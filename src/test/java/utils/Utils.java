package utils;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public class Utils {
    public static final int USER_ID = 111111;
    private static final String ACCESS_TOKEN = "token";

    public VkApiClient vk() {
        TransportClient transportClient = HttpTransportClient.getInstance();
        return new VkApiClient(transportClient);
    }

    public UserActor actor() {
        return new UserActor(USER_ID, ACCESS_TOKEN);
    }
}