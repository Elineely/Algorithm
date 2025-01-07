-- 코드를 입력하세요
SELECT CAR_ID, round(sum(datediff(end_date, start_date) + 1) / count(car_id), 1) as AVERAGE_DURATION
FROM CAR_Rental_company_Rental_history
group by car_id
having average_duration >= 7
order by average_duration desc, car_id desc