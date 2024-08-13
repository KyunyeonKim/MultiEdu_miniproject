package member;

import java.util.List;

public interface MemberDao {

    public int insert(MemberVO vo);
    public int update(MemberVO vo);
    public int delete(MemberVO vo);
    public MemberVO selectOne(MemberVO vo);
    public List<MemberVO>adminSelectAll();
    public List<MemberVO>adminsearchList(String searchKey, String searchWord);
    public int login(String member_id, String pw);

}
