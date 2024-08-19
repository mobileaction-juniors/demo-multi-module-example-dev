package co.mobileaction.example.worker.client;

public interface IHttpRequestExecutor
{
    <T> T executeGetRequest(String url, Class<T> resultClass);
}
