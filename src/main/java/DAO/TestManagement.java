package DAO;

import DI.IServiceLocator;
import Interfaces.*;
import main.utils.ConnectionInfo;
import main.utils.IDatabaseManager;

import java.sql.*;
import java.util.ArrayList;

public class TestManagement implements ITestManagement {
    private IDatabaseManager connectionManager;

    public TestManagement(IServiceLocator serviceLocator) {
        connectionManager = serviceLocator.getService("IDatabaseManager");
    }

    @Override
    public TestInfo createTest(OrganisationInfo organisationInfo) {
        TestInfo test = new TestInfo(-1);
        return test;
    }

    @Override
    public void updateTest(TestInfo testInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return;

            PreparedStatement st;
            int start;
            if (testInfo.getId() <= 0) {
                st = con.prepareStatement("insert into onlinetest.test(org_id, subject, timer, test_name, isPublic, number_of_questions) value (?,?,?,?,?,?)");
                start = 1;
            } else {
                st = con.prepareStatement("update onlinetest.test set org_id=?, subject=?,timer=?,test_name=?,isPublic=?,number_of_questions=? where test_id=?");
                st.setLong(7, testInfo.getId());
                start = 1;
            }
            st.setLong(start + 0, testInfo.getOrgId());
            st.setString(start + 1, testInfo.getSubjectName());
            st.setInt(start + 2, testInfo.getTimer());
            st.setString(start + 3, testInfo.getTestName());
            st.setBoolean(start + 4, testInfo.isPublic());
            st.setInt(start + 5, testInfo.getNumberOfQuestions());

            st.execute();
            st.close();

            if (testInfo.getId() <= 0) {
                st = con.prepareStatement("SELECT LAST_INSERT_ID()");
                ResultSet resultSet = st.executeQuery();

                if (!resultSet.next()) {
                    //???
                } else {
                    long newId = resultSet.getLong(1);
                    testInfo.internalUpdateIdForDAOUseOnly(newId);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public void removeTest(TestInfo testInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return;
            PreparedStatement st = con.prepareStatement("delete from onlinetest.test where test_id=?");
            st.setLong(1, testInfo.getId());

            st.execute();
            st.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public ArrayList<TestInfo> getTestInfo(OrganisationInfo organisationInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return null;
            String check = "select test_id, org_id, subject, timer, test_name, isPublic, number_of_questions from onlinetest.test where org_id=?";
            PreparedStatement st = con.prepareStatement(check);
            st.setLong(1,organisationInfo.getId());

            ResultSet resultSet = st.executeQuery();

            ArrayList<TestInfo> result = new ArrayList<>();
            while (resultSet.next()) {
                TestInfo holder = new TestInfo(resultSet.getLong(1));

                holder.setOrgId(resultSet.getLong(2));
                holder.setSubjectName(resultSet.getNString(3));
//                holder.setTimer(resultSet.getInt(4));
                holder.setTimer(10);
                holder.setTestName(resultSet.getNString(5));
                holder.setPublic(resultSet.getBoolean(6));
                holder.setNumberOfQuestions(resultSet.getInt(7));

                result.add(holder);
            }
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public ArrayList<TestInfo> getTestInfoBySubject(String subjectName) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return null;
            String check = "select test_id, org_id, subject, timer, test_name, isPublic, number_of_questions from onlinetest.test where subject=?";
            PreparedStatement st = con.prepareStatement(check);
            st.setString(1,subjectName);

            ResultSet resultSet = st.executeQuery();

            ArrayList<TestInfo> result = new ArrayList<>();
            while (resultSet.next()) {
                TestInfo holder = new TestInfo(resultSet.getLong(1));

                holder.setOrgId(resultSet.getLong(2));
                holder.setSubjectName(resultSet.getNString(3));
//                holder.setTimer(resultSet.getInt(4));
                holder.setTimer(10);
                holder.setTestName(resultSet.getNString(5));
                holder.setPublic(resultSet.getBoolean(6));
                holder.setNumberOfQuestions(resultSet.getInt(7));

                result.add(holder);
            }
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public TestInfo getTestInfoByName(String testName) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return null;
            String check = "select test_id, org_id, subject, timer, test_name, isPublic, number_of_questions from onlinetest.test where test_name=?";
            PreparedStatement st = con.prepareStatement(check);
            st.setString(1,testName);

            ResultSet resultSet = st.executeQuery();

            if (!resultSet.next()) {
                st.close();
                return null;
            } else {
                TestInfo holder = new TestInfo(resultSet.getLong(1));

                holder.setOrgId(resultSet.getLong(2));
                holder.setSubjectName(resultSet.getNString(3));
//                holder.setTimer(resultSet.getInt(4));
                holder.setTestName(resultSet.getNString(5));
                holder.setPublic(resultSet.getBoolean(6));
                holder.setNumberOfQuestions(resultSet.getInt(7));

                return holder;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public boolean testHasQuestions(TestInfo testInfo) {
        ArrayList<QuestionInfo> list = getQuestionInfo(testInfo);
        if (list.isEmpty()){
            return true;
        } else return false;
    }

    @Override
    public int getNumberOfNonFilledQuestions(TestInfo testInfo) {
        ArrayList<QuestionInfo> list = getQuestionInfo(testInfo);
        int numberOfQuestions=testInfo.getNumberOfQuestions();
        return numberOfQuestions-list.size();
    }

    @Override
    public boolean testExist(OrganisationInfo organisationInfo, String testName) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return false;
            String check = "select 1 from onlinetest.test where test_name=?";
            PreparedStatement st = con.prepareStatement(check);
            st.setString(1, testName);

            ResultSet resultSet = st.executeQuery();

            st.execute();
            st.close();

            return resultSet.next();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public QuestionInfo createQuestion(TestInfo testInfo) {
        QuestionInfo question = new QuestionInfo(-1);
        return question;
    }

    @Override
    public void updateQuestion(QuestionInfo questionInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return;

            PreparedStatement st;
            if (questionInfo.getId() <= 0) {
                st = con.prepareStatement("insert into onlinetest.question(test_id, type, number_of_answers, type_of_answers, content, score_value) value (?,?,?,?,?,?)");
            } else {
                st = con.prepareStatement("update onlinetest.question set test_id=?,type=?,number_of_answers=?,type_of_answers=?,content=?,score_value=? where question_id=?");
                st.setLong(7, questionInfo.getId());
            }
            st.setLong(1, questionInfo.getTestId().getId());
            st.setString(2, questionInfo.getType());
            st.setInt(3, questionInfo.getNumberOfAnswers());
            st.setString(4, questionInfo.getTypeOfAnswers());
            st.setString(5, questionInfo.getContent());
            st.setInt(6, questionInfo.getScoreValue());

            st.execute();
            st.close();

            if (questionInfo.getId() <= 0) {
                st = con.prepareStatement("SELECT LAST_INSERT_ID()");
                ResultSet resultSet = st.executeQuery();

                if (!resultSet.next()) {
                    //???
                } else {
                    long newId = resultSet.getLong(1);
                    questionInfo.internalUpdateIdForDAOUseOnly(newId);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public void removeQuestion(QuestionInfo questionInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return;
            PreparedStatement st = con.prepareStatement("delete from onlinetest.question where question_id=?");
            st.setLong(1, questionInfo.getId());

            st.execute();
            st.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public ArrayList<QuestionInfo> getQuestionInfo(TestInfo testInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return null;
            String check = "select question_id, type, number_of_answers, type_of_answers, content, score_value from onlinetest.question where test_id=?";
            PreparedStatement st = con.prepareStatement(check);
            st.setLong(1, testInfo.getId());

            ResultSet resultSet = st.executeQuery();

            ArrayList<QuestionInfo> result = new ArrayList<>();
            while (resultSet.next()) {
                QuestionInfo holder = new QuestionInfo(resultSet.getLong(1));

                holder.setTestId(testInfo);
                holder.setType(resultSet.getNString(2));
                holder.setNumberOfAnswers(resultSet.getInt(3));
                holder.setTypeOfAnswers(resultSet.getNString(4));
                holder.setContent(resultSet.getNString(5));
                holder.setScoreValue(resultSet.getInt(6));

                result.add(holder);
            }
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public QuestionInfo getQuestionInfoById(long questionId) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return null;
            String check = "select test_id, type, number_of_answers, type_of_answers, content, score_value from onlinetest.question where question_id=?";
            PreparedStatement st = con.prepareStatement(check);
            st.setLong(1, questionId);

            ResultSet resultSet = st.executeQuery();


            if (!resultSet.next()) {
                st.close();
                return null;
            } else {
                QuestionInfo holder = new QuestionInfo(questionId);

//                holder.setTestId(resultSet.getLong(1));
                holder.setType(resultSet.getNString(2));
                holder.setNumberOfAnswers(resultSet.getInt(3));
                holder.setTypeOfAnswers(resultSet.getNString(4));
                holder.setContent(resultSet.getNString(5));
                holder.setScoreValue(resultSet.getInt(6));

                return holder;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public boolean questionExist(TestInfo testInfo, String content) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return false;
            String check = "select 1 from onlinetest.question where test_id=? and content=?";

            PreparedStatement st = con.prepareStatement(check);
            st.setLong(1, testInfo.getId());
            st.setString(2, content);

            ResultSet resultSet = st.executeQuery();

            st.execute();
            st.close();

            return resultSet.next();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public AnswerInfo createAnswer(QuestionInfo questionInfo) {
        AnswerInfo answer = new AnswerInfo(-1);
        return answer;
    }

    @Override
    public void updateAnswer(AnswerInfo answerInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return;

            PreparedStatement st;
            if (answerInfo.getId() <= 0) {
                st = con.prepareStatement("insert into onlinetest.answer(type, content, is_correct, question_id) value (?,?,?,?)");
            } else {
                st = con.prepareStatement("update onlinetest.answer set type=?,content=?,is_correct=?,question_id=? where answer_id=?");
                st.setLong(5, answerInfo.getId());
            }
            st.setString( 1, answerInfo.getType());
            st.setString(2, answerInfo.getContent());
            st.setBoolean(3,answerInfo.isCorrect());
            st.setLong(4, answerInfo.getQuestionId().getId());

            st.execute();
            st.close();

            if (answerInfo.getId() <= 0) {
                st = con.prepareStatement("SELECT LAST_INSERT_ID()");
                ResultSet resultSet = st.executeQuery();

                if (!resultSet.next()) {
                    //???
                } else {
                    long newId = resultSet.getLong(1);
                    answerInfo.internalUpdateIdForDAOUseOnly(newId);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public void removeAnswer(AnswerInfo answerInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return;
            PreparedStatement st = con.prepareStatement("delete from onlinetest.answer where answer_id=?");
            st.setLong(1, answerInfo.getId());

            st.execute();
            st.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public ArrayList<AnswerInfo> getAllAnswersInfo(QuestionInfo questionInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return null;
            String check = "select answer_id, type, content, is_correct from onlinetest.answer where question_id=?"; //???
            PreparedStatement st = con.prepareStatement(check);
            st.setLong(1, questionInfo.getId());

            ResultSet resultSet = st.executeQuery();

            ArrayList<AnswerInfo> result = new ArrayList<>();
            while (resultSet.next()) {
                AnswerInfo holder = new AnswerInfo(resultSet.getLong(1));

                holder.setType(resultSet.getNString(2));
                holder.setContent(resultSet.getNString(3));
                holder.setCorrect(resultSet.getBoolean(4));
                holder.setQuestionId(questionInfo);

                result.add(holder);
            }
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public AnswerInfo getSpecificAnswerInfo(QuestionInfo questionInfo, AnswerInfo answerInfo) {
        return null;
    }

    @Override
    public ArrayList<AnswerInfo> getCorrectAnswers(QuestionInfo questionInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return null;
            String check = "select answer_id, type, content from onlinetest.answer where question_id=? and is_correct=true"; //???
            PreparedStatement st = con.prepareStatement(check);
            st.setLong(1, questionInfo.getId());

            ResultSet resultSet = st.executeQuery();

            ArrayList<AnswerInfo> result = new ArrayList<>();
            while (resultSet.next()) {
                AnswerInfo holder = new AnswerInfo(resultSet.getLong(1));

                holder.setType(resultSet.getNString(2));
                holder.setContent(resultSet.getNString(3));
                holder.setCorrect(true);
                holder.setQuestionId(questionInfo);

                result.add(holder);
            }
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public boolean answerCorrect(AnswerInfo answerInfo) {
        return answerInfo.isCorrect(); //toDelete
    }

    @Override
    public boolean answerExist(QuestionInfo questionInfo, String content) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return false;
            String check = "select 1 from onlinetest.answer where question_id=? and content=?";

            PreparedStatement st = con.prepareStatement(check);
            st.setLong(1, questionInfo.getId());
            st.setString(2, content);

            ResultSet resultSet = st.executeQuery();

            st.execute();
            st.close();

            return resultSet.next();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public int getNumberOfNonFilledAnswers(QuestionInfo questionInfo) {
        ArrayList<AnswerInfo> list = getAllAnswersInfo(questionInfo);
        int numberOfAnswers=questionInfo.getNumberOfAnswers();
        return numberOfAnswers-list.size();
    }

    @Override
    public void addAuthor(TestInfo testInfo, UserInfo userInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return;
            String check = "insert into onlinetest.authors(test_id, user_id) value (?,?)"; //???

            PreparedStatement st = con.prepareStatement(check);
            st.setLong(1, testInfo.getId());
            st.setLong(2, userInfo.getId());

            st.execute();
            st.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public void removeAuthor(TestInfo testInfo, UserInfo userInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return;
            PreparedStatement st = con.prepareStatement("delete from onlinetest.authors where test_id=? and user_id=?");
            st.setLong(1, testInfo.getId());
            st.setLong(1, userInfo.getId());

            st.execute();
            st.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public ArrayList<UserInfo> getAuthorsInfo(TestInfo testInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return null;
            String check = "select u.user_id, organisation_id, role, first_name, last_name, email, is_active from onlinetest.authors a join onlinetest.user u on a.user_id = u.user_id where test_id=?"; //???
            PreparedStatement st = con.prepareStatement(check);
            st.setLong(1, testInfo.getId());

            ResultSet resultSet = st.executeQuery();

            ArrayList<UserInfo> result = new ArrayList<>();
            while (resultSet.next()) {
                UserInfo holder = new UserInfo(resultSet.getLong(1));     //??????????

                holder.setOrgId(resultSet.getLong(2));
                holder.setRole(resultSet.getNString(3));
                holder.setFirstName(resultSet.getNString(4));
                holder.setLastName(resultSet.getNString(5));
                holder.setEmail(resultSet.getNString(6));
                holder.setIsActive(resultSet.getInt(7));

                result.add(holder);
            }
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public boolean authorExist(TestInfo testInfo, UserInfo userInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return false;
            String check = "select 1 from onlinetest.authors where test_id=? and user_id=?";

            PreparedStatement st = con.prepareStatement(check);
            st.setLong(1, testInfo.getId());
            st.setLong(2, userInfo.getId());

            ResultSet resultSet = st.executeQuery();

            st.execute();
            st.close();

            return resultSet.next();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            connectionManager.release(ci);
        }
    }

}
