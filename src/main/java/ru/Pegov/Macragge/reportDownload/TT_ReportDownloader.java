package ru.Pegov.Macragge.reportDownload;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class TT_ReportDownloader {
    private String url = "http://swr.transtk.ru/api/CrmOnyma/TT/TT";
    private MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();;

    public TT_ReportDownloader() {
    }

    public byte[] getReport(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        uriVariables.add("export_data", "{\"filters\":[{\"name\":\"filter\",\"val\":\"Contains([macroregion], 'Урал')\"},{\"name\":\"export_not_column\",\"val\":[]},{\"name\":\"grid_column\",\"val\":{\"application_number\":\"№ Заявки\",\"abonent_fio\":\"ФИО абонента\",\"address\":\"Адрес подкл\",\"dogNum\":\"Номер договор\",\"dog_status\":\"Статус договора\",\"phone\":\"Телефон\",\"noteTxt\":\"Описание проблемы\",\"macroregion\":\"МР\",\"city\":\"Город\",\"name_int\":\"Процесс\",\"current_step\":\"Шаг\",\"application_category\":\"Категория\",\"start_2ltp\":\"Решение о переводе на 2ЛТП\",\"start_dt\":\"Дата открытия ТТ\",\"application_finish_user\":\"ФИО сотрудника, завершившего процесс по заявке\",\"end_dt\":\"Дата закрытия ТТ\",\"start_dt_upprocess\":\"Дата открытия обращения\",\"durat_days_hrs\":\"Продолжи тельность\",\"application_status\":\"Статус\",\"durat\":\"Общая длит-ть, ч\",\"resp_user\":\"Последний ответственный\",\"sec_line_start\":\"Посл. перевод на 2ЛТП\",\"sec_to_th_line_start\":\"Посл. перевод с 2ЛТП на 3ЛТП\",\"durat_2ltp\":\"Общ. время ТТ в ЗО 2 ЛТП\",\"th_line_start\":\"Посл. перевод на 3ЛТП\",\"from_th_line\":\"Посл. перевод с 3ЛТП\",\"th_line_durat\":\"Общ. время ТТ в ЗО 3 ЛТП\",\"ch_trip_time\":\"Переносился ли выезд\",\"trip_in_time\":\"Выезд осущ. вовремя\",\"trip_cnt\":\"Кол-во выездов\",\"oth_proc_ex\":\"Обращение повт.\",\"oth_proc_cnt\":\"Кол-во ТТ тех. кат. по аб. за 30 дн.\",\"oth_proc_link\":\"№ пред. обращений\",\"oth_proc_close_step\":\"Пред. обращ-е закрыто на\",\"clev_cnt\":\"Кол-во заявок на выезд\",\"clev_create\":\"Создание заявки на выезд\",\"clev_susceptible\":\"Изменение статуса Новая - > Подтверждена\",\"clev_work\":\"Изменение статуса Подтверждена -> В работе\",\"clev_implemented\":\"Изменение статуса В работе -> Реализована\",\"clev_not_implemented\":\"Изменение статуса В работе -> Не Реализована\",\"clev_close\":\"Изменение статуса на закрытая\",\"clev_cancel\":\"Изменение статуса на отменена\",\"clev\":\"Время реализации заявки\",\"clev_date\":\"Начало тайм-слота\",\"step_end\":\"Шаг завершения\",\"action_end\":\"Действие завершения\",\"OTS_CLASS_CLOSE_LOCK_1\":\"2ЛТП Услуга\",\"OTS_CLASS_CLOSE_LOCK_2\":\"2ЛТП Группа\",\"OTS_CLASS_CLOSE_LOCK_3\":\"2ЛТП Подгруппа\",\"OTS_CLASS_CLOSE_LOCK_4\":\"2ЛТП Причина\",\"OTS_CLASS_CLOSE_LOCK_5\":\"2ЛТП Подпричина\",\"OTS_CLASS_CLOSE_CALL_1\":\"3ЛТП Услуга\",\"OTS_CLASS_CLOSE_CALL_2\":\"3ЛТП Группа\",\"OTS_CLASS_CLOSE_CALL_3\":\"3ЛТП Подгруппа\",\"OTS_CLASS_CLOSE_CALL_4\":\"3ЛТП Причина\",\"OTS_CLASS_CLOSE_CALL_5\":\"3ЛТП Подпричина\"}},{\"name\":\"start\",\"val\":\"11.1.2021\"},{\"name\":\"end\",\"val\":\"13.1.2021\"},{\"name\":\"file_name\",\"val\":\"Отчет по ТТ\"},{\"name\":\"file_type\",\"val\":\"Xlsx\"},{\"name\":\"repid\",\"val\":127}]}");
        uriVariables.add("dtStart", "11.01.2021");
        uriVariables.add("dtEnd","13.01.2021");
        uriVariables.add("dgv$DXFREditorcol7","Урал");
        uriVariables.add("export_file_name","123");
        uriVariables.add("export_file_type_VI","Xlsx");
        uriVariables.add("export_file_type","Xlsx");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(uriVariables, headers);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity( url, request , byte[].class).getBody();
    }
}
