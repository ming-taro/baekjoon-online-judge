SELECT f1.ID, f2.FISH_NAME, f1.LENGTH
FROM FISH_INFO f1 JOIN FISH_NAME_INFO f2
ON f1.FISH_TYPE = f2.FISH_TYPE
WHERE (f1.FISH_TYPE, f1.LENGTH) IN (SELECT FISH_TYPE, MAX(LENGTH) AS LENGTH
                                    FROM FISH_INFO
                                    WHERE LENGTH > 10
                                    GROUP BY FISH_TYPE)
ORDER BY ID