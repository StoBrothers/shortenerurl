/**
 * Created by isaykin on 05.11.2015.
 */

function init() {
    $('.filter-docs, .filter-year').find('li').click(function (e) {
        $(this).closest('ul').find('li.active').removeClass('active');
        $(this).addClass('active');
        setFilters();
        e.preventDefault();
    });

    var page = location.pathname;
    page = page.split('/');
    for (var i in page) {
        if (page[i].length == 0) page.splice(i, 1);
    }

    $('.searchBtn').click(function (e) {
        setFilters();
        e.preventDefault();
    });

    $('.searchInput').on('keyup', function(e){
        if(e.keyCode == 13 && $.trim($(this).val()).length != 0){
            setFilters();
        }
    });
    
    var search = decodeURIComponent(page.pop());
    var year = page.pop();
    var docs = page.pop();
    if (search !== 'all') {
        $('.searchInput').val(search);
    }
    $('.filter-docs').find('li[data-value="' + docs + '"]').addClass('active');
    $('.filter-year').find('li[data-value="' + year + '"]').addClass('active');
    $('.filter-docs, .filter-year').each(function (e) {
        var text = $(this).find('li.active').text();
        $(this).find('.filter-text').text(text);
    });
    setFilters(true);
}

function setFilters(createUrl) {
    createUrl = createUrl || false;
    var d = $('.filter-docs li.active').data('value');
    var y = $('.filter-year li.active').data('value');
    var search = $.trim($('.searchInput').val());
    if (typeof d === 'undefined') d = 'all';
    if (typeof y === 'undefined') y = new Date().getFullYear();
    if (search.length == 0) search = 'all';
    var url = location.protocol + '//' + location.host + '/requests/' + d + '/' + y + '/' + search + '/';
    var urlD1 = location.protocol + '//' + location.host + '/download/requests/' + d + '/' + y + '/' + search + '/log_registration/';
    var urlD2 = location.protocol + '//' + location.host + '/download/requests/' + d + '/' + y + '/' + search + '/log_accounting/';
    if (createUrl) {
        $('.btn-group-links-create a').each(function(){
            var a = $(this).attr('href');
            a = location.protocol + '//' + location.host + '/' + a + '/create/' + y + '/';
            $(this).attr('href', a);            
        });
        $('.linkExport_LogReg').attr('href', urlD1);
        $('.linkExport_LogAccount').attr('href', urlD2);
    } else {
        location.href = url;
    }
}