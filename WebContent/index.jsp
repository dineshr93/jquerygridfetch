

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- <!DOCTYPE html>

<html lang="en" mode="normal"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     -->
     <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="http://getbootstrap.com/assets/ico/favicon.ico">

    <title>OneEmpower</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
<link class="me" href="css/jquery-ui-1.10.4.custom.min.css" rel="stylesheet"> <!-- to be changed -->
<link href="css/ui.jqgrid.css" rel="stylesheet">
<link href="css/ui.multiselect.css" rel="stylesheet">

<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
    <style type="text/css">
        html, body { font-size: 75%; }
	body { padding-top: 70px; }
	#fixedbutton {
    position: fixed;
    bottom: 70px;
    right: 700px; 
}
    </style>

    <!-- Custom styles for this template -->
 <!--   <link href="http://getbootstrap.com/examples/navbar-fixed-top/navbar-fixed-top.css" rel="stylesheet"> -->

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  

</script>


<script src="js/jquery-1.7.1.min.js"></script>

<script src="js/bootstrap-dropdown.js"></script>
 
<% String json = (String)request.getAttribute("val");   %>                      <!-- -important call -->
       
           
    <script type="text/javascript">
   
    var  jso = <%=json%>;
    <%-- alert(jso); --%> 

        $(document).ready(function () {
            'use strict';
           <%--  <% /* Object value = request.getAttribute("val");*/ %> --%>
        <%--   <p><%=value%></p> --%>

/* <div class=jsndata data-jsn="${val}" > </div>
 */       
               var mydata=jso,
                $grid = $("#list"),
                initDateEdit = function (elem) {
                    setTimeout(function () {
                        $(elem).datepicker({
                            dateFormat: 'dd-M-yy',
                            autoSize: true,
                            changeYear: true,
                            changeMonth: true,
                            showButtonPanel: true,
                            showWeek: true
                        });
                    }, 100);
                },
                initDateSearch = function (elem) {
                    setTimeout(function () {
                        $(elem).datepicker({
                            dateFormat: 'dd-M-yy',
                            autoSize: true,
                            changeYear: true,
                            changeMonth: true,
                            showWeek: true,
                            showButtonPanel: true
                        });
                    }, 100);
                },
                numberTemplate = {formatter: 'number', align: 'right', sorttype: 'number', editable: true,
                    searchoptions: { sopt: ['eq', 'ne', 'lt', 'le', 'gt', 'ge', 'nu', 'nn', 'in', 'ni'] }};
        
            $grid.jqGrid({
                datatype: 'local',
                data: mydata,
                colNames: [/*'Inv No', */'Client', 'Date', 'Amount', 'Tax', 'Total', 'Closed', 'Shipped via', 'Notes'],
                colModel: [
                    //{ name: 'id', index: 'id', width: 70, align: 'center', sorttype: 'int', searchoptions: { sopt: ['eq', 'ne']} },
                    { name: 'name', index: 'name', editable: true, width: 70, editrules: { required: true},
                        editoptions: { dataInit: function (elem) { $(elem).addClass('ui-state-highlight'); }}},
                    { name: 'invdate', index: 'invdate', width: 80, align: 'center', sorttype: 'date',
                        formatter: 'date', formatoptions: { newformat: 'd-M-Y' }, editable: true, datefmt: 'd-M-Y',
                        editoptions: { dataInit: initDateEdit },
                        searchoptions: { sopt: ['eq', 'ne', 'lt', 'le', 'gt', 'ge'], dataInit: initDateSearch } },
                    { name: 'amount', index: 'amount', width: 80, template: numberTemplate },
                    { name: 'tax', index: 'tax', width: 55, template: numberTemplate,
                        editoptions: { dataInit: function (elem) { $(elem).addClass('ui-state-error'); }} },
                    { name: 'total', index: 'total', width: 65, template: numberTemplate },
                    {name: 'closed', index: 'closed', width: 75, align: 'center', editable: true, formatter: 'checkbox',
                        edittype: 'checkbox', editoptions: {value: 'Yes:No', defaultValue: 'Yes'},
                        stype: 'select', searchoptions: { sopt: ['eq', 'ne'], value: ':Any;true:Yes;false:No' } },
                    {name: 'ship_via', index: 'ship_via', width: 105, align: 'center', editable: true, formatter: 'select',
                        edittype: 'select', editoptions: { value: 'FE:FedEx;TN:TNT;IN:Intim', defaultValue: 'IN' },
                        stype: 'select', searchoptions: { sopt: ['eq', 'ne'], value: ':Any;FE:FedEx;TN:TNT;IN:IN' } },
                    { name: 'note', index: 'note', width: 60, sortable: false, editable: true, edittype: 'textarea' }
                ],
                rowNum: 10,
                rowList: [5, 10, 20],
                pager: '#pager',
                gridview: true,
                rownumbers: true,
                autoencode: true,
                ignoreCase: true,
                sortname: 'invdate',
                viewrecords: true,
                sortorder: 'desc',
                height: '100%',
                caption: 'Demonstrate how to use the columnChooser'
            });
            $grid.jqGrid('navGrid', '#pager', {refreshstate: 'current', add: true, edit: true, del: true});
            $.extend(true, $.ui.multiselect, {
                locale: {
                    addAll: 'Make all visible',
                    removeAll: 'Hidde All',
                    itemsCount: 'Avlialble Columns'
                }
            });
            //$.extend(true, $.jgrid.col, {
            //    width: 500,
            //    msel_opts: {dividerLocation: 0.5}
            //});
            $grid.jqGrid('navButtonAdd', '#pager', {
                caption: "",
                buttonicon: "ui-icon-calculator",
                title: "Choose columns",
                onClickButton: function () {
                    $(this).jqGrid('columnChooser',
                        {width: 550, msel_opts: {dividerLocation: 0.5}});
                    //$(this).jqGrid('columnChooser');
                    $("#colchooser_" + $.jgrid.jqID(this.id) + ' div.available>div.actions')
                        .prepend('<label style="float:left;position:relative;margin-left:0.6em;top:0.6em">Search:</label>');
                }
            });
        });

    </script>






</head>





  <body>
 <%-- ${val } --%>
 <form action="Loginservlet1" method="get">
  <!-- <script>var jso = ${val}; 
document.write(jso);
    </script>  -->
     <<div class=jsndata data-jsn="${val}" > </div> 
  <input type="submit" id=fixedbutton value="get values" >
  </form> 
  
      <!-- Fixed navbar -->
   <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="">One Empower</a>
        </div>
        <div class="navbar-collapse collapse" style="height: 5px;">
           <ul class="nav navbar-nav">
                <li class="active"><a href="./index1.html">Home</a></li>
                <li><a href="http://www.oneempower.com/aboutus_company.html">About us</a></li>
                <li><a href="http://www.oneempower.com/solutions.html">Solutions</a></li>
                <li><a href="http://www.oneempower.com/customers.html">Customers</a></li>
               <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#">Switch Theme<b class="caret"></b></a>

    <ul class="dropdown-menu">
        <li> <a data-theme="cssm/jquery-ui-1.10.4.custom.min.css">Dark</a> 
        </li>
        <li> <a data-theme="csse/jquery-ui-1.10.4.custom.min.css">Apple</a> 
        </li>
        <li> <a data-theme="cssb/jquery-ui-1.10.4.custom.min.css">Blue</a> 
        </li>
        <li> <a data-theme="cssc/jquery-ui-1.10.4.custom.min.css">Pearl</a> 
        </li>
        <li> <a data-theme="cssd/jquery-ui-1.10.4.custom.min.css">Box</a> 
        </li>
<li> <a data-theme="cssf/jquery-ui-1.10.4.custom.min.css">Pane</a> 
        </li>
<li> <a data-theme="cssg/jquery-ui-1.10.4.custom.min.css">Glue</a> 
        </li>
<li> <a data-theme="cssh/jquery-ui-1.10.4.custom.min.css">Interim</a> 
        </li>
<li> <a data-theme="cssi/jquery-ui-1.10.4.custom.min.css">frale</a> 
        </li>
<li> <a data-theme="cssj/jquery-ui-1.10.4.custom.min.css">Box</a> 
        </li>
<li> <a data-theme="cssk/jquery-ui-1.10.4.custom.min.css">torr</a> 
        </li>
<li> <a data-theme="cssl/jquery-ui-1.10.4.custom.min.css">Vine</a> 
        </li>

      </ul>
    </li>
            </ul>
          
        </div><!--/.nav-collapse -->
      </div>
    </div>         
        </div>
    </div>
</div>

    <div class="container"> 

      <!-- Main component for a primary marketing message or call to action -->
    <div class="table-responsive" >
	<div id="content" style="float:center">
   	 <table id="list"><tr><td/></tr></table>
    	 <div id="pager"></div>
	</div> </div>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->

<script src="js/jquery-ui.min.js"></script>
<script src="js/ui.multiselect.js"></script> 
<script src="js/grid.locale-en.js"></script>
<script src="js/jquery.jqGrid.src.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="ls/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

<script src="js/jquery.debouncedresize.js"></script>
<script src="js/jquery.throttledresize.js"></script>

  
<script>
$(window).load(function () {
        var link = $('link.me');
       $('ul.dropdown-menu li').click(function (e) {
        e.preventDefault();
        var theme = $(this).find('a').data('theme');
        var store = $(this).index();
        //console.log(store);
        localStorage.setItem("colors", store);
        link.attr('href', theme)
       });
      var retrievedObject = localStorage.getItem('colors');
      if (retrievedObject != null) {
        $('ul.dropdown-menu li:eq(' + retrievedObject + ')').trigger('click');
      } else {
        $('ul.dropdown-menu li:eq(0)').click();
      }
  });

</script>

</body></div></html>