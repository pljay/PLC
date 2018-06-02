 //点击行选中
 function inputselect() {
            var $thr = $('table thead tr');  
            var $tbr = $('table tbody tr');  
            var $checkAllTh = $('<th><input name="btSelectAll" type="checkbox"  /></th>'); 
              /*“全选/反选”复选框*/
            var $checkAll = $thr.find('input');
            $checkAll.click(function(event){
                        /*将所有行的选中状态设成全选框的选中状态*/
                     $tbr.find('input').prop('checked',$(this).prop('checked'));
                      /*并调整所有选中行的CSS样式*/
                         if ($(this).prop('checked')) {
                             $tbr.find('input').parent().parent().addClass('warning');
                         } else{
                            $tbr.find('input').parent().parent().removeClass('warning');
                         }
                       /*阻止向上冒泡，以防再次触发点击操作*/
                         event.stopPropagation();
            });
            /*点击全选框所在单元格时也触发全选框的点击操作*/
             
            $checkAllTh.click(function(){
                         $(this).find('input').click();
                         });
            /*点击每一行的选中复选框时*/
            $tbr.find('input').click(function(event){
            /*调整选中行的CSS样式*/
            $(this).parent().parent().toggleClass('warning');
            /*如果已经被选中行的行数等于表格的数据行数，将全选框设为选中状态，否则设为未选中状态*/
            $checkAll.prop('checked',$tbr.find('input:checked').length == $tbr.length ? true : false);
                       /*阻止向上冒泡，以防再次触发点击操作*/
                        event.stopPropagation();
                         });
            /*点击每一行时也触发该行的选中操作*/
            $tbr.click(function(){
                         $(this).find('input').click();
            });
    };
    function findcheckedval() {
            var chk_value =[];//定义一个数组   
            $('input[name="btSelectItem"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数  
            chk_value.push($(this).val());//将选中的值添加到数组chk_value中
            }); 
            console.log(chk_value);
            return  chk_value;
    }