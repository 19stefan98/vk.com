package pages;

import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.likes.Type;
import com.vk.api.sdk.objects.likes.responses.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Utils;

public class Likes extends Utils{
    protected static final Logger LOGGER = LogManager.getLogger();

    public Object addLike(UserActor actor, Type type, Integer itemId) {
        try {
            AddResponse addResponse = vk().likes().add(actor, type, itemId).execute();
            LOGGER.info("Method addLike: number of likes " + addResponse.getLikes());
            return addResponse.getLikes();
        } catch (ApiException | ClientException e) {
            LOGGER.error(e.getMessage());
            return e.getMessage();
        }
    }

    public Object deleteLike(UserActor actor, Type type, Integer itemId) {
        try {
            DeleteResponse deleteResponse = vk().likes().delete(actor, type, itemId).execute();
            LOGGER.info("Method deleteLike: number of likes " + deleteResponse.getLikes());
            return deleteResponse.getLikes();
        } catch (ApiException | ClientException e) {
            LOGGER.error(e.getMessage());
            return e.getMessage();
        }
    }

    public Object getListLike(UserActor actor, Type type, Integer itemId) {
        try {
            GetListResponse getListResponse = vk().likes().getList(actor, type).itemId(itemId).execute();
            LOGGER.info("Method getListLike: user id list " + getListResponse.getItems());
            return getListResponse.getItems();
        } catch (ApiException | ClientException e) {
            LOGGER.error(e.getMessage());
            return e.getMessage();
        }
    }

    public String isLiked(UserActor actor, Type type, Integer itemId) {
        try {
            IsLikedResponse isLikedResponse = vk().likes().isLiked(actor, type, itemId).execute();
            LOGGER.info("Method isLiked: item in the list i like " + isLikedResponse.getLiked().getValue());
            return isLikedResponse.getLiked().getValue();
        } catch (ApiException | ClientException e) {
            LOGGER.error(e.getMessage());
            return e.getMessage();
        }
    }
}
