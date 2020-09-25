package DI;

public interface IServiceLocator {
    <T> T getService(String name);
}
