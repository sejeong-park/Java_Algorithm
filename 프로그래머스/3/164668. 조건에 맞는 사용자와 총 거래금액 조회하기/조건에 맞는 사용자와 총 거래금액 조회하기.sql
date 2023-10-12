-- 완료된 중고 거래의 총 금액이 70만원 이상인 사람 -> 총거래 금액 기준 오름차순 정렬

# select writer_id, sum(price) as total from used_goods_board group by writer_id having sum(price) >= 700000


select user.USER_ID, user.NICKNAME, topuser.total as TOTAL_SALES
from used_goods_user as user
inner join (
    select writer_id, sum(price) as total
    from used_goods_board
    where status = "DONE"
    group by writer_id
    having sum(price) >= 700000
) as topuser
on user.user_id = topuser.writer_id
order by total_sales asc;
