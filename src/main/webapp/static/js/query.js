var currentQueryData;
var dataRowMax = 10;
var indexBegin = 0;
var indexCurrent = 0;
var indexEnd;
var queryWords;

var test = [

];

$(document).ready(function(){
  var width =  $("body").width();
  if(width > 768){
    $("#html5_3d_animation").html5_3d_animation({
      window_width: width,
      window_height: $("body").height(),
      window_background: '#222222',
      star_count: '500',
      star_color: '#ffffff',
      star_depth: '200'
    });
  }


  function query(){
    $("#myModal").modal('show');
    $("#accordion").css("display","none");
    $("#queryResult").empty();
    $(".panel-footer").css("display","none");
    queryWords = $("#inputWords").val();



    var info = {
      words:queryWords
    };

    $.ajax({
      type :"post",
      url: "rest/query/retrieval",
      data:JSON.stringify(info),
      dataType:"json",
      contentType: "application/json; charset=utf-8",
      async:true,
      success:function(msg){
        if(msg.length == 0){
          $("#accordion").text("没有找到结果");
          return;
        }
        $("#accordion").css("display","block").text("为您找到相关结果约 "+msg.length+" 个");
        analyzeChartData(msg);
        getChartDataRange(indexBegin);
        $(".panel-footer").css("display","block");
        $("#myModal").modal('hide');
      }
    });
  }

  $("#queryButton").click(function(){
    query();
  });

  $("#inputWords").keydown(function(event){
    if(event.which==13){
      query();
      event.preventDefault();
    }

  });


  $(".before-button").click(function(){
    getChartDataRange(--indexCurrent);
  });

  $(".after-button").click(function(){
    getChartDataRange(++indexCurrent);
  });

  function analyzeChartData(data){
    currentQueryData = data;
    var len = data.length;
    indexBegin = 0;
    indexCurrent = 0;
    indexEnd = Math.floor(len / dataRowMax) - 1;
    if(len % dataRowMax != 0){
      indexEnd++;
    }
  }

  function getChartDataRange(index){
    if(index < 0){
      indexCurrent = 0;
      return;
    }
    if(index > indexEnd){
      indexCurrent = indexEnd;
      return;
    }

    var dataShow = [];
    for(var i = 0; i < dataRowMax;i++){
      var len = currentQueryData.length;
      var indexData = indexCurrent * dataRowMax + i;
      if(indexData < len){
        dataShow.push(currentQueryData[indexData]);
      }
      else{
        break;
      }

    }
    showResult(dataShow);
  }

  function showResult(data){


    var words = queryWords.split(" ");
    var $ul = $("<ul/>");
    $.each(data,function(index,content){
      var $li = $("<li class=\"left clearfix\" />");
      var $chat_body = $("<div class=\"chat-body clearfix\"/>");


      for(var i=0; i < words.length; i++){
        if(words[i] != ""){
          content.title = content.title.replace(new RegExp(words[i].toLowerCase(),'g'), "<span style=\"color:rgb(197,0,0);\">"+words[i]+"</span>");
          content.body = content.body.replace(new RegExp(words[i].toLowerCase(),'g'), "<span style=\"color:rgb(241,31,1);\">"+words[i]+"</span>");
        }
      }
      $chat_body.append("<div class='header'><strong class='primary-font'><a href='"+content.url+"'>"+content.title+"</a></strong></div>");
      $chat_body.append("<p>"+content.body+"</p>");
      $ul.append($li.append($chat_body));
    });

    $("#queryResult").empty().append($ul);
  }
});