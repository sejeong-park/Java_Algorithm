SELECT COUNT(ID) AS FISH_COUNT
FROM FISH_INFO
WHERE IFNULL(LENGTH,10) <= 10;