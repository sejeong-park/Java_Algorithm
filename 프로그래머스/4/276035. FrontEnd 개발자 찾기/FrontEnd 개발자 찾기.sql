SELECT 
    D.ID,
    D.EMAIL,
    D.FIRST_NAME,
    D.LAST_NAME
FROM DEVELOPERS AS D
WHERE D.SKILL_CODE & (
    SELECT 
        SUM(CODE)
    FROM SKILLCODES
    WHERE CATEGORY = 'Front End'
)
ORDER BY ID;