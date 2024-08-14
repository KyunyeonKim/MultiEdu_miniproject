package dislike;

public interface DislikeDAO {
    int addDislike(DisLikeVO dislike);
    boolean isDisliked(DisLikeVO dislike);
    int getDislikeCount(int productId);
}
