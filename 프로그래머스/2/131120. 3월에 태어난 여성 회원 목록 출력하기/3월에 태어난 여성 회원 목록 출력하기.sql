-- 코드를 입력하세요
SELECT Member_id, member_name, gender, date_format(date_of_birth, '%Y-%m-%d') as DATE_OF_BIRTHDAY
FROM member_profile
where date_of_birth Like '%-03-%' and gender='W' and TLNO IS NOT NULL
order by member_id asc;