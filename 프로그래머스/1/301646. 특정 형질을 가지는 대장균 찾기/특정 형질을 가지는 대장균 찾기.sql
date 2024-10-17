-- 2번 형질이 보유하지 않으면서 1번이나 3번 형질을 보유하고 있는 대장균

SELECT 
    COUNT(*) AS COUNT
FROM ECOLI_DATA
WHERE 
    (GENOTYPE & 2) != 2
    AND ((GENOTYPE & 4) = 4 OR (GENOTYPE & 1) = 1);
