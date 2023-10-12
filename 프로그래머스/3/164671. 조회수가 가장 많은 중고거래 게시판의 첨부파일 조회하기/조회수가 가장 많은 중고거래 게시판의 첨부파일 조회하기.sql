-- 조회수가 가장 높은 중고거래 게시물에 대한 첨부파일 경로

# with topview as (select board_id, max(views) from used_goods_board)

# select concat("/home/grep/src/", topview.board_id, "/", file_id, file_name, file_ext) as FILE_PATH from used_goods_file inner join topview on used_goods_file.board_id = topview.board_id order by used_goods_file.file_id desc

# SELECT MAX(VIEWS) INTO @MAX_VIEWS FROM USED_GOODS_BOARD;

# SELECT CONCAT('/home/grep/src/',F.BOARD_ID,'/',FILE_ID,FILE_NAME,FILE_EXT) 
# FROM USED_GOODS_FILE F 
# INNER JOIN USED_GOODS_BOARD B ON F.BOARD_ID = B.BOARD_ID AND B.VIEWS=@MAX_VIEWS
# ORDER BY 1 DESC

WITH TEMP_TABLE AS (SELECT F.BOARD_ID
                    FROM USED_GOODS_BOARD B
                    INNER JOIN USED_GOODS_FILE F
                    ON B.BOARD_ID = F.BOARD_ID
                    ORDER BY B.VIEWS DESC
                   LIMIT 1)

SELECT CONCAT('/home/grep/src/', B.BOARD_ID, '/', F.FILE_ID, F.FILE_NAME, F.FILE_EXT) AS FILE_FATH
FROM USED_GOODS_BOARD B, USED_GOODS_FILE F
WHERE B.BOARD_ID = F.BOARD_ID
AND B.BOARD_ID = (SELECT BOARD_ID FROM TEMP_TABLE)
ORDER BY F.FILE_ID DESC;