-- 코드를 입력하세요
SELECT WAREHOUSE_ID, WAREHOUSE_NAME, ADDRESS,
CASE
    WHEN FREEZER_YN IS NULL OR FREEZER_YN = "N" THEN "N"
    ELSE "Y"
    END FREEZER_YN
FROM FOOD_WAREHOUSE
WHERE SUBSTRING(ADDRESS, 1, 3) = "경기도"
ORDER BY WAREHOUSE_ID