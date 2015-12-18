var w_w,w_h,w_b,s_c,s_color,s_d;
var dom,fov,SCREEN_WIDTH,SCREEN_HEIGHT,HALF_WIDTH,HALF_HEIGHT;
var c_id,numPoints;
var range=400;
var delta = 200;
var loop;

var speed_slow = 65;
var canvas;
var c;
var points = [];

(function(a){
    //noinspection JSDuplicatedDeclaration


    a.fn.html5_3d_animation=function(p){
        var p=p||{};

        w_w=p&&p.window_width?p.window_width:"500";
        w_h=p&&p.window_height?p.window_height:"400";
        w_b=p&&p.window_background?p.window_background:"#000";
        s_c=p&&p.star_count?p.star_count:"600";
        s_color=p&&p.star_color?p.star_color:"#FFF";
        s_d=p&&p.star_depth?p.star_depth:"250";
        dom=a(this);
        fov = parseInt(s_d);
        SCREEN_WIDTH = parseInt(w_w);
        SCREEN_HEIGHT = parseInt(w_h);
        HALF_WIDTH = SCREEN_WIDTH/2;
        HALF_HEIGHT = SCREEN_HEIGHT/2;
        c_id = dom.attr("id");
        numPoints = s_c;

        canvas = document.getElementById(c_id);
        c = canvas.getContext('2d');
        dom.attr({ width: w_w, height: w_h});
        setup(c,points);

    }
})(jQuery);

function setup(c,points) {
    initPoints(points);
    loop = setInterval(function(){
        render(c,points);
    }, speed_slow);
}

function draw3Din2D(point3d,c){
    var x3d = point3d[0];
    var y3d = point3d[1];
    var z3d = point3d[2];
    var scale = fov/(fov+z3d);
    var x2d = (x3d * scale) + HALF_WIDTH;
    var y2d = (y3d * scale)  + HALF_HEIGHT;

    c.lineWidth= scale;
    c.strokeStyle = s_color;
    c.beginPath();
    c.moveTo(x2d,y2d);
    c.lineTo(x2d+scale,y2d);
    c.stroke();
}

function initPoints(points) {
    for (var i=0; i<numPoints; i++){
        var point = [(Math.random()*range)-delta, (Math.random()*range)-delta , (Math.random()*range)-delta ];
        points.push(point);
    }
}

function render(c,points) {
    c.fillStyle=w_b;
    c.fillRect(0,0, SCREEN_WIDTH, SCREEN_HEIGHT);

    for (var i=0; i<numPoints; i++){
        var point3d = points[i];

        var z3d = point3d[2];
        z3d-=1;
        if(z3d<-fov) z3d +=400;
        point3d[2] = z3d;
        draw3Din2D(point3d,c);

    }
}