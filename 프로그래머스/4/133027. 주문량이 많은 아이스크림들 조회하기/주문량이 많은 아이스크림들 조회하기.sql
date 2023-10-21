SELECT FIRST_HALF.FLAVOR
FROM FIRST_HALF JOIN JULY
ON FIRST_HALF.FLAVOR = JULY.FLAVOR
GROUP BY FLAVOR
ORDER BY SUM(FIRST_HALF.TOTAL_ORDER) + SUM(JULY.TOTAL_ORDER) DESC
LIMIT 3