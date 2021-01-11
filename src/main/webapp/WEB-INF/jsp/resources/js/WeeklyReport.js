var weeklyReport;

var maxDateVarGlobal = maxDate();

//Ебанный костыль, но так работает и хуй с ним. Ненавижу JS
function maxDate() {
        var maxDateVar;
                axios.post('/Macragge/getReport',
                    {report: "MaxDate"}
                ).then(function(response){
                        maxDateVar = new Date(response.data.data.maxDate);
                        console.log(maxDateVar) 
                        weeklyReport.fp.config._maxDate = maxDateVar;
                        weeklyReport.fp.redraw();
                    }
                )
        return maxDateVar;
        }

window.onload = function() {

Vue.component('report-ttmainreport', { 
        props:['reportdata'],
	template: `
<div v-if="reportdata.data">
<table  width="1450" style="border-collapse:collapse;">
    <tbody>
    <tr height="8">
        <td width="1446" colspan="25" bgcolor="#bcd2ee" >
        <div align="center"><font size="1" color="blue">Отчет за период
        </font></div>
        </td>
    </tr>
    <tr height="8">
    <td width="108" rowspan="3" class="header" >
    <div align="center"><font size="1">МР</font></div>
    </td><td width="71" rowspan="3" class="header" >
    <div align="center"><font size="1">Город</font></div>
    </td><td width="1304" colspan="23" class="header" >
    <div align="center"><font size="1">Обслуживание и качество.
    Отчетный период 00:00 пн - 23:59 вс. В отчет попадают
    заявки: 1. X=Все закрытые за отчетный период
    2. Y=Не завершенные на 23:59 воскресенья отчетной
    недели, длительность решения которых превышает
    24ч. Для незавершенных заявок длительность
    определяется от момента регистрации до
    23:59 воскресенья отчетной недели. </font></div>
    </td>
    </tr>
    <tr height="8" class="header">
    <td width="71" rowspan="2" class="header" >
    <div align="center"><font size="1">Количество заявок закрытых
    за отчетную неделю (X)</font></div>
    </td><td width="86" rowspan="2" class="header" >
    <div align="center"><font size="1">Количество заявок не завершенных
    на 23:59 вс, с временем решения более 24ч (Y)</font></div>
    </td><td width="152" colspan="4" class="header" >
    <div align="center"><font size="1">Количество заявок решенных
    в срок или длящихся на 23:59 вс отчетной недели</font></div>
    </td><td width="81" rowspan="2" class="header" >
    <div align="center"><font size="1">Среднее время решения
    заявки на техническую поддержку, час</font></div>
    </td><td width="79" rowspan="2" class="header" >
    <div align="center"><font size="1">Количество повторных
    заявок на техническую поддержку</font></div>
    </td><td width="86" rowspan="2" class="header" >
    <div align="center"><font size="1">Количество заявок из X,
    решенных на второй линии технической поддержки,
    без выезда</font></div>
    </td><td width="133" colspan="4" class="header" >
    <div align="center"><font size="1">Количество заявок из N=X+Y,
    решенных, обработанных или находящихся
    на 23:59 вс на второй линии.</font></div>
    </td><td width="83" rowspan="2" class="header" >
    <div align="center"><font size="1">Среднее время решения
    или обработки заявки абонента 2-ой линией
    тех. поддержки на выборке N=X+Y, час</font></div>
    </td><td width="98" rowspan="2" class="header" >
    <div align="center"><font size="1">Количество повторных
    заявок из N=X+Y на тех. поддержку, в которых
    предыдущее обращение было закрыто на 2-ой
    линии тех. поддержки</font></div>
    </td><td width="87" rowspan="2" class="header" >
    <div align="center"><font size="1">Количество заявок из N,
    решенных на третьей линии (с выездом) технической
    поддержки.</font></div>
    </td><td width="157" colspan="4" class="header" >
    <div align="center"><font size="1">Количество заявок из N=X+Y,
    решенных или находящихся на третьей линии
    (выезд) на 23:59вс. </font></div>
    </td><td width="76" rowspan="2" class="header" >
    <div align="center"><font size="1">Среднее время решения
    заявки абонента 3-ей линией тех. поддержки
    на выборке N, ч</font></div>
    </td><td width="97" rowspan="2" class="header" >
    <div align="center"><font size="1">Количество повторных
    заявок из N на тех. поддержку, в которых
    предыдущее обращение было закрыто на 3-ей
    линии тех. поддержки</font></div>
    </td>
    </td><td width="97" rowspan="2" class="header" >
    <div align="center"><font size="1">Среднее время
    "Решение о переводе на 2ЛТП" час.
    </font></div>
    </td>
    </tr>
    <tr height="8">
    <td width="23" class="header" >
    <div align="center"><font size="1">l до 36ч</font></div>
    </td><td width="44" class="header" >
    <div align="center"><font size="1">36ч-48ч</font></div>
    </td><td width="44" class="header" >
    <div align="center"><font size="1">48ч-72ч</font></div>
    </td><td width="38" class="header" >
    <div align="center"><font size="1">свыше 72ч</font></div>
    </td><td width="21" class="header" >
    <div align="center"><font size="1">до 6ч</font></div>
    </td><td width="34" class="header" >
    <div align="center"><font size="1">6ч-8ч</font></div>
    </td><td width="34" class="header" >
    <div align="center"><font size="1">8-24ч</font></div>
    </td><td width="40" class="header" >
    <div align="center"><font size="1">свыше 24ч</font></div>
    </td><td width="24" class="header" >
    <div align="center"><font size="1">до 36ч</font></div>
    </td><td width="45" class="header" >
    <div align="center"><font size="1">36ч-48ч</font></div>
    </td><td width="45" class="header" >
    <div align="center"><font size="1">48ч-72ч</font></div>
    </td><td width="39" class="header" >
    <div align="center"><font size="1">свыше 72ч</font></div>
    </td></tr>
    
    <tr v-for="row in reportdata.data.cityList">
        <td width="68" >
        <div align="center"><font size="1">{{row.regionName}}</font></div>
        </td><td width="71" >
        <div align="center"><font size="1">{{row.name}}</font></div>
        </td><td width="71" >
        <div align="center"><font size="1">{{row.countL2TP}}</font></div>
        </td><td width="86" >
        <div align="center"><font size="1">{{row.atWorkCount}}</font></div>
        </td><td width="23" >
        <div align="center"><font size="1">{{row.compleateAt36h}}</font></div>
        </td><td width="44" >
        <div align="center"><font size="1">{{row.compleateAt48h}}</font></div>
        </td><td width="44" >
        <div align="center"><font size="1">{{row.compleateAt72h}}</font></div>
        </td><td width="38" >
        <div align="center"><font size="1">{{row.compleateAtMore72h}}</font></div>
        </td><td width="81" >
        <div align="center"><font size="1">{{((row.averegeTimeL23TP).toFixed(2)).toString().replace('.',',')}}</font></div>
        </td><td width="79" >
        <div align="center"><font size="1">{{row.repeatedTTL2+row.repeatedTTL3}}</font></div>
        </td><td width="86" >
        <div align="center"><font size="1">{{row.countL2TP+row.atWorkCount-row.countL3TP}}</font></div>
        </td><td width="21" >
        <div align="center"><font size="1">{{row.compleateAt6hL2}}</font></div>
        </td><td width="34" >
        <div align="center"><font size="1">{{row.compleateAt8hL2}}</font></div>
        </td><td width="34" >
        <div align="center"><font size="1">{{row.compleateAt24hL2}}</font></div>
        </td><td width="40" >
        <div align="center"><font size="1">{{row.compleateAtMore24hL2}}</font></div>
        </td><td width="83" >
        <div align="center"><font size="1">{{(row.averegeTimeL2TP).toFixed(2).toString().replace('.',',')}}</font></div>
        </td><td width="98" >
        <div align="center"><font size="1">{{Math.round(row.repeatedTTL2/8)}}</font></div>
        </td><td width="87" >
        <div align="center"><font size="1">{{row.countL3TP}}</font></div>
        </td><td width="24" >
        <div align="center"><font size="1">{{row.compleateAt36hL3}}</font></div>
        </td><td width="45" >
        <div align="center"><font size="1">{{row.compleateAt48hL3}}</font></div>
        </td><td width="45" >
        <div align="center"><font size="1">{{row.compleateAt72hL3}}</font></div>
        </td><td width="39" >
        <div align="center"><font size="1">{{row.compleateAtMore72hL3}}</font></div>
        </td><td width="76" >
        <div align="center"><font size="1">{{row.averegeTimeL3TP.toFixed(2).toString().replace('.',',')}}</font></div>
        </td><td width="97" >
        <div align="center"><font size="1">{{row.repeatedTTL3}}</font></div>
        </td>
        </td><td width="97" >
        <div align="center"><font size="1">{{(row.decisionTime).toFixed(2).toString().replace('.',',')}}</font></div>
        </td>
    </tr>   
</tbody></table>
        
    <div style="position: relative; float: left; padding: 10px">
        Были на ремонте в магнитогорске более 24х часов
        <ol>
            <li v-for="id in reportdata.data.timeMoreThen24h">
                {{id}}
            </li>
        </ol>
    </div>
    <div style="position: relative; float: left; padding: 10px">
        Повторные обращения на 3ЛТП Магнитогорск
        <ol>
            <li v-for="id in reportdata.data.repeated3LTPMGN">
                {{id}}
            </li>
        </ol>
    </div>
    <div style="position: relative; float: left; padding: 10px">
        Повторные обращения на 3ЛТП без Магнитогорска
        <ol>
            <li v-for="id in reportdata.data.repeated3LTP">
                {{id}}
            </li>
        </ol>
    </div>
    <div style="position: relative; float: left; padding: 10px">
        Среднее время за период:
        <ol>
            <li>2ЛТП: {{reportdata.data.avgTimeOn2LTP}}</li>
            <li>3ЛТП: {{reportdata.data.avgTimeOn3LTP}}</li>
        </ol>
    </div>
</div>
`
})

Vue.component('report-employeereport', { 
        props:['reportdata'],
	template: `
            <div v-if="reportdata.data">
                <table style="border-collapse:collapse;">
                <tr>
                    <td class="header"> ФИО </td>
                    <td class="header"> Количество </td>
                </tr>
                <tr v-for="row in reportdata.data.reportBody">
                    <td> {{row.name}} </td>
                    <td> {{row.count}} </td>
                </tr>
                </table>
            </div>
        `
})

Vue.component('report-buildingsreport', { 
        props:['reportdata'],
        data: function () {
            return {
              curentCityObject: null
            }
          },
	template: `
            <div v-if="reportdata.data">
            <div style="position: relative; float: top; padding: 10px"> Пока только Магнитогорск(МИ) </div>
                <table style="border-collapse:collapse; position: relative; float: left;">
                <tr>
                    <td class="header"> Адрес</td>
                    <td class="header"> Количество </td>
                </tr>
                <tr v-for="row in reportdata.data.reportBody"
                    v-on:click = "curentCityObject = row">
                    <td> {{row.name}} </td>
                    <td> {{row.count}} </td>
                </tr>
                </table>
        
                <div style="position: relative; float: left; padding: 10px"
                    v-if="curentCityObject !== null ">
                    Ремонты по адресу {{curentCityObject.name}}
                    <table style="border-collapse:collapse;">
                        <tr>
                            <td>
                                Лицевой счет
                            </td>
                            <td>
                                Номер ТТ
                            </td>
                            <td>
                                Причина занесения
                            </td>
                        </tr>
                        <tr  v-for="tt in curentCityObject.ttlist">
                            <td>
                                {{tt.clientId}}
                            </td>
                            <td>
                                {{tt.id}}
                            </td>
                            <td>
                                {{tt.reason}}
                            </td>
                        </tr>
                    </table>
                </div>
        
            </div>
        `
})


weeklyReport = new Vue({
     el: '#dataPicker',
  data: {
    fp: flatpickr("#myID", {
            time_24hr: true,
            enableTime: false,
            mode: "range",
            weekNumbers: true,
            maxDate: maxDateVarGlobal,
            onClose: function(selectedDates, dateStr, instance){
                selectedDates[1].setSeconds(59);
                selectedDates[1].setMinutes(59);
                selectedDates[1].setHours(23);
                weeklyReport.sendRequest();
            }
        }),
    reportsList: [
            newReport('TTMainReport','Полный отчет'),
            newReport('EmployeeReport','По сотрудникам'),
            newReport('BuildingsReport','По домам')
            ],
    currentReport: 'TTMainReport',
    currentReportData: {message: 'not ready'}
  },
  methods:{
      sendRequest: function (){
        var data = {
            report: this.currentReport,
            from: this.fp.selectedDates[0].getTime(),
            to: this.fp.selectedDates[1].getTime(),
            additionalData: {}
        };
        
        axios.post('/Macragge/getReport',
            data
        ).then(function(response){
                weeklyReport.currentReportData = response.data;
                console.log(weeklyReport.currentReportData);
            }
        );
      }
  },
  computed: {
    currentTabComponent: function () {
      return 'report-' + this.currentReport.toLowerCase()
    }
  }
});  

function newReport(name, humanUnderstandingName){
    return {
        name: name,
        HUName: humanUnderstandingName
    };
}
};

