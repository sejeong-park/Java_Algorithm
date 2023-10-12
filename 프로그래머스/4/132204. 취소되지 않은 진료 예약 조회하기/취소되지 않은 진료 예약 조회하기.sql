-- 2022년 4월 13일 취소되지 않은 흉부외과 진료 예약 내역 조회
-- APNT_NO, PT_NAME, PT_NO, MCDP_CD, DR_NAME, APNT_YND

select table1.APNT_NO, table1.PT_NAME, table1.PT_NO, table1.MCDP_CD, DOCTOR.DR_NAME, table1.APNT_YMD
from (select APNT_YMD, APNT_NO, PATIENT.PT_NO, PATIENT.PT_NAME, MCDP_CD, MDDR_ID
        from (
            select APNT_YMD, APNT_NO, PT_NO, MCDP_CD, MDDR_ID from appointment
            where apnt_cncl_yn = "N" 
            and mcdp_cd = "CS"
            and DATE_FORMAT(APNT_YMD, "%Y-%m-%d") = "2022-04-13"
        ) as cs_appointment
            inner join patient on cs_appointment.PT_NO = patient.PT_NO) as table1
inner join DOCTOR on table1.MDDR_ID = DOCTOR.DR_ID
order by table1.APNT_YMD ASC
