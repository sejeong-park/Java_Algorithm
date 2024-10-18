SELECT 
    CASE
        WHEN 
            SUM(CASE WHEN SC.NAME = 'Python' THEN 1 ELSE 0 END) > 0
            AND SUM(CASE WHEN SC.CATEGORY = 'Front End' THEN 1 ELSE 0 END) > 0
            THEN 'A'
        WHEN 
            SUM(CASE WHEN SC.NAME = 'C#' THEN 1 ELSE 0 END) > 0
            THEN 'B'
        WHEN SUM(CASE WHEN SC.CATEGORY = 'Front End' THEN 1 ELSE 0 END) > 0
            THEN 'C'
        ELSE NULL
    END AS GRADE,
    D.ID,
    D.EMAIL
FROM DEVELOPERS AS D
JOIN SKILLCODES AS SC
ON D.SKILL_CODE & SC.CODE <> 0
GROUP BY D.ID, D.EMAIL
HAVING GRADE IS NOT NULL
ORDER BY GRADE, ID;
