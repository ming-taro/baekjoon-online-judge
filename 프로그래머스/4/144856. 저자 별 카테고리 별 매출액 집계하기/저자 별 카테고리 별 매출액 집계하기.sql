SELECT A.AUTHOR_ID AS AUTHOR_ID, A.AUTHOR_NAME, A.CATEGORY AS CATEGORY, SUM(A.PRICE*B.SALES) AS TOTAL_SALES
FROM BOOK_SALES B JOIN (SELECT B.*, A.AUTHOR_NAME
                  FROM BOOK B JOIN AUTHOR A
                  WHERE B.AUTHOR_ID = A.AUTHOR_ID) A
WHERE B.BOOK_ID = A.BOOK_ID AND YEAR(B.SALES_DATE) = 2022 AND MONTH(B.SALES_DATE) = 1
GROUP BY A.AUTHOR_ID, A.CATEGORY
ORDER BY AUTHOR_ID, CATEGORY DESC