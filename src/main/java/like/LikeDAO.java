package like;

import like.LikeVO;

public interface LikeDAO {
    int addLike(LikeVO like);
    boolean isLiked(LikeVO like);
    int getLikeCount(int productId);
}
