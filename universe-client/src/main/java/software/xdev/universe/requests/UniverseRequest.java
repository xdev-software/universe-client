package software.xdev.universe.requests;

public interface UniverseRequest<T>
{
	Class<T> getResponseClass();
}
