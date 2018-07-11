var url = location.href;
alert('url: ' + url);
//取得網址

var ary = url.split('/')[11].split('.');
alert('ary: ' + ary);
//以"/"分割存入陣列，再以"."分割

var id = ary[0];
alert('id: ' + id);
//取得Id

id = 2;

var conn=new ActiveXObject("ADODB.Connection");
var rs=new ActiveXObject("ADODB.Recordset");
conn.ConnectionString = "jdbc:sqlserver://localhost:1433;databaseName=Servlet_HW;user=sa;password=passw0rd;";
conn.open;
var abc = rs.open("SELECT * FROM Product WHERE Product_ID="+ id +";");
// ,conn,3,3
var abc1 = url.split(',');
alert('abc: ' + abc1[1]);