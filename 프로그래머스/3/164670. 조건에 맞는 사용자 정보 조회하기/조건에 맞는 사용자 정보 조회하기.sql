-- 1. 중고 거래 게시물을 3건 이상 등록한 사용자 
-- substring (target column, 몇번째 시작인지, 몇글자인지)
with user as(
    select writer_id
    from used_goods_board as board 
    group by board.writer_id 
    having count(board_id) >= 3
)

select USER_ID, NICKNAME, CONCAT(city, " ", street_address1,  " ",street_address2) as "전체주소", 
concat(substring(tlno, 1, 3), '-'
      , substring(tlno, 4, 4), '-'
      , substring(tlno, 8, 4)) as "전화번호"
from used_goods_user 
inner join user 
on used_goods_user.user_id = user.writer_id
order by user_id desc;