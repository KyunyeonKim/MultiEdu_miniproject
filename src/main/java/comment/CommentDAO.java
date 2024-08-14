package comment;

import java.util.List;

public interface CommentDAO {

    public int commentinsert(CommentVO vo);

    public int commentupdate(CommentVO vo);

    public int commentdelete(CommentVO vo);

    public List<CommentVO>commentselect(int productId);



}
