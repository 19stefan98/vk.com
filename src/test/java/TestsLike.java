import com.vk.api.sdk.objects.likes.Type;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Likes;

public class TestsLike extends Likes{

    @DataProvider(name = "id post", parallel = true)
    public Object[][] dpMethod(){
        return new Object[][] {{4188}, {4179}};
    }

    @Test(groups = "add_like", description = "add like for post", dataProvider = "id post")
    public void testAddLike(Integer data) {
        Object getLike = addLike(actor(), Type.POST, data);
        Assert.assertNotEquals(getLike, 0, "like is not added");
    }

    @Test(groups = "add_like", description = "adding a post that does not exist")
    public void testAddLikeError() {
        Object getLike = addLike(actor(), Type.POST, 111111);
        Assert.assertNotNull(getLike, "empty error");
    }

    @Test(groups = "delete_like", description = "delete like under post")
    public void testDeleteLike() {
        addLike(actor(), Type.POST, 4188);
        Object getDelete = deleteLike(actor(), Type.POST, 4188);
        Assert.assertEquals(getDelete, 0, "like is not deleted");
    }

    @Test(groups = "delete_like", description = "delete undelivered like")
    public void testDeleteLikeError() {
        Object getDelete = deleteLike(actor(), Type.POST, 4188);
        Assert.assertEquals(getDelete, "Access denied (15): Access denied","empty error");
    }

    @Test(groups = "get_list_like", description = "get list likes")
    public void testGetListLike() {
        addLike(actor(), Type.POST, 4188);
        Object getListLike = getListLike(actor(), Type.POST, 4188);
        Assert.assertNotNull(getListLike,"list is empty");
    }

    @Test(groups = "get_list_like", description = "get not existent list of likes")
    public void testGetListLikeError() {
        deleteLike(actor(), Type.POST, 41881);
        Object getListLike = getListLike(actor(), Type.POST, 41881);
        Assert.assertNotNull(getListLike,"list is empty");
    }

    @Test(groups = "is_liked", description = "is liked post for user")
    public void testIsLiked() {
        addLike(actor(), Type.POST, 4188);
        String liked = isLiked(actor(), Type.POST, 4188);
        Assert.assertEquals(liked, "1", "user is not list is liked");
    }

    @Test(groups = "is_liked", description = "lack of mark like for user")
    public void testIsLikedError() {
        deleteLike(actor(), Type.POST, 4188);
        String isLiked = isLiked(actor(), Type.POST, 4188);
        Assert.assertEquals(isLiked, "0", "user in list is liked");
    }
}
