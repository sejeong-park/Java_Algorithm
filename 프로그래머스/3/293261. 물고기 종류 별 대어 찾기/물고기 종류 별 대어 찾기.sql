SELECT 
    FI.ID, 
    FN.FISH_NAME, 
    FI.LENGTH
FROM FISH_INFO AS FI
JOIN FISH_NAME_INFO AS FN
ON FI.FISH_TYPE = FN.FISH_TYPE
WHERE LENGTH = (
    SELECT MAX(LENGTH)
    FROM FISH_INFO
    WHERE FISH_TYPE = FI.FISH_TYPE
)
ORDER BY ID;