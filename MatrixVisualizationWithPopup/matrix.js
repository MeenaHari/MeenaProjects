
function build_instance_matrix(json, svg, src, dst) {
  var matrix = [], linknodes = [], lidx = 0,
      nodes = json.nodes,
      n = nodes.length;

  // use 3x3 matrix for instance matrix
  for (var i=0; i < 3; i++) {
	  matrix[i] = d3.range(n).map(function(j) { return {x: j, y: i, z: 0}; });
	  linknodes[i] = {srcInstName: "inst" + (i+1), 
		  	  dstInstName: "inst" + (i+1), 
			  x: -1, 
			  y: -1};
  }

  // Convert links to matrix; count character occurrences.
  json.links.forEach(function(link) {

    var srcindex, trgtindex;
    if (src == "all" && dst == "all") {
	    for (var idx in nodes) {
		    if (nodes[idx].name == link.source)
			  srcindex = idx;
		    if (nodes[idx].name == link.target)
		  	  trgtindex = idx;
	    }
	    matrix[srcindex][trgtindex].z += link.value;
	    matrix[trgtindex][srcindex].z += link.value;
	    matrix[srcindex][srcindex].z += link.value;
	    matrix[trgtindex][trgtindex].z += link.value;
	    nodes[srcindex].count += link.value;
	    nodes[trgtindex].count += link.value;
    } else {
	    if (link.source == src && link.target == dst) {
		    srcindex = link.srcInst - 1;
		    trgtindex = link.destInst - 1;
		    matrix[trgtindex][srcindex].z = link.value;
		    linknodes[lidx].x = srcindex;
		    linknodes[lidx].y = trgtindex;
		    lidx++;
	    }
    }
  });

  // Precompute the orders.
    var orders = {
	name: d3.range(n).sort(function(a, b) { return d3.ascending(nodes[a].name, nodes[b].name); }),
	count: d3.range(n).sort(function(a, b) { return nodes[b].count - nodes[a].count; }),
	group: d3.range(n).sort(function(a, b) {
	    var x = nodes[b].group - nodes[a].group;
	    return (x != 0) ?  x : d3.ascending(nodes[a].name, nodes[b].name);
	}),
    };

  // The default sort order.
  x.domain(orders.name);

  var width1 = 360, height1 = 360;

  svg.append("rect")
      .attr("class", "background")
      .attr("width", width1)
      .attr("height", height1);

  var row = svg.selectAll(".row")
      .data(matrix)
    .enter().append("g")
      .attr("id", function(d, i) { return "row"+i; })
      .attr("class", "row")
      //.attr("transform", function(d, i) { return "translate(0," + x(i) + ")"; })
      .attr("transform", function(d, i) { return "translate(0," + (i*120) + ")"; })
      .each(row);

  row.append("line")
      .attr("x2", width1);

  row.append("text")
      .attr("x", -6)
      .attr("y", x.rangeBand() / 2)
      .attr("dy", ".32em")
      .attr("text-anchor", "end")
      .text(function(d, i) { return linknodes[i].srcInstName; });

  var column = svg.selectAll(".column")
      .data(matrix)
    .enter().append("g")
      .attr("id", function(d, i) { return "col"+i; })
      .attr("class", "column")
      //.attr("transform", function(d, i) { return "translate(" + x(i) + ")rotate(-90)"; });
      .attr("transform", function(d, i) { return "translate(" + (i*120) + ")rotate(-90)"; });

  column.append("line")
      .attr("x1", -width1);

  column.append("text")
      .attr("x", 6)
      .attr("y", x.rangeBand() / 2)
      .attr("dy", ".32em")
      .attr("text-anchor", "start")
      .text(function(d, i) { return linknodes[i].dstInstName; });


  function row(row) {
    var cell = d3.select(this).selectAll(".cell")
	  .data(row.filter(function(d) { return d.z; }))
      .enter().append("rect")
        .attr("class", "cell")
        //.attr("x", function(d) { return x(d.x); })
        .attr("x", function(d) { return d.x * 120;})
        .attr("width", x.rangeBand())
        .attr("height", x.rangeBand())
        .style("fill-opacity", function(d) { return z(d.z); })
        .style("fill", function(d) {
		var color = [ d3.rgb("green"), d3.rgb("magenta"), d3.rgb("blue"), d3.rgb("orange"), d3.rgb("brown"),
	       		      d3.rgb("pink"), d3.rgb("purple"), d3.rgb("violet"), d3.rgb("yellow"), d3.rgb("red") ];
		var color_idx = parseInt(matrix[d.y][d.x].z/1000);
		console.log("x=" + d.x + " y=" + d.y + " matrix[x][y]=" +
			matrix[d.y][d.x] + " z=" + matrix[d.y][d.x].z + "\n");
		console.log("row=" + row + " color_idx=" + color_idx + "\n");
		if (matrix[d.y][d.x].z == 0) {
			return "#eee";
		}
		if (color_idx > 10)
			color_idx = 10;
		return color[color_idx];
	})
	
  }

  return matrix;
}

function build_matrix(json, svg, src, dst) {
  var matrix = [],
      nodes = json.nodes,
      n = nodes.length;

  // Compute index per node.
  nodes.forEach(function(node, i) {
    node.index = i;
    node.count = 0;
    matrix[i] = d3.range(n).map(function(j) { return {x: j, y: i, z: 0}; });
  });

  // Convert links to matrix; count character occurrences.
  json.links.forEach(function(link) {

    var srcindex, trgtindex;
    if (src == "all" && dst == "all") {
	    for (var idx in nodes) {
		    if (nodes[idx].name == link.source)
			  srcindex = idx;
		    if (nodes[idx].name == link.target)
		  	  trgtindex = idx;
	    }
	    matrix[srcindex][trgtindex].z += link.value;
	    matrix[trgtindex][srcindex].z += link.value;
	    matrix[srcindex][srcindex].z += link.value;
	    matrix[trgtindex][trgtindex].z += link.value;
	    nodes[srcindex].count += link.value;
	    nodes[trgtindex].count += link.value;
    } else {
	    if (link.source == src && link.target == dst) {
		    srcindex = link.srcInst;
		    trgtindex = link.destInst;
		    matrix[srcindex][trgtindex].z += link.value;
		    matrix[trgtindex][srcindex].z += link.value;
		    matrix[srcindex][srcindex].z += link.value;
		    matrix[trgtindex][trgtindex].z += link.value;
	    }
    }
  });

  // Precompute the orders.
    var orders = {
	name: d3.range(n).sort(function(a, b) { return d3.ascending(nodes[a].name, nodes[b].name); }),
	count: d3.range(n).sort(function(a, b) { return nodes[b].count - nodes[a].count; }),
	group: d3.range(n).sort(function(a, b) {
	    var x = nodes[b].group - nodes[a].group;
	    return (x != 0) ?  x : d3.ascending(nodes[a].name, nodes[b].name);
	}),
    };

  // The default sort order.
  x.domain(orders.name);

  svg.append("rect")
      .attr("class", "background")
      .attr("width", width)
      .attr("height", height);

  var row = svg.selectAll(".row")
      .data(matrix)
    .enter().append("g")
      .attr("id", function(d, i) { return "row"+i; })
      .attr("class", "row")
      .attr("transform", function(d, i) { return "translate(0," + x(i) + ")"; })
      .each(row);

  row.append("line")
      .attr("x2", width);
	 
  row.append("text")
      .attr("x", -6)
      .attr("y", x.rangeBand() / 2)
      .attr("dy", ".32em")
      .attr("text-anchor", "end")
      .text(function(d, i) { return nodes[i].name; });

  var column = svg.selectAll(".column")
      .data(matrix)
    .enter().append("g")
      .attr("id", function(d, i) { return "col"+i; })
      .attr("class", "column")
      .attr("transform", function(d, i) { return "translate(" + x(i) + ")rotate(-90)"; });

  column.append("line")
      .attr("x1", -width);

  column.append("text")
      .attr("x", 6)
      .attr("y", x.rangeBand() / 2)
      .attr("dy", ".32em")
      .attr("text-anchor", "start")
      .text(function(d, i) { return nodes[i].name; });


  function row(row) {
    var cell = d3.select(this).selectAll(".cell")
	  .data(row.filter(function(d) { return d.z; }))
      .enter().append("rect")
        .attr("class", "cell")
        .attr("x", function(d) { return x(d.x); })
        .attr("width", x.rangeBand())
        .attr("height", x.rangeBand())
        .style("fill-opacity", function(d) { return z(d.z); })
        .style("fill", function(d) {
		var color = [ d3.rgb("green"), d3.rgb("magenta"), d3.rgb("blue"), d3.rgb("orange"), d3.rgb("brown"),
	       		      d3.rgb("pink"), d3.rgb("purple"), d3.rgb("violet"), d3.rgb("yellow"), d3.rgb("red") ];
		var color_idx = parseInt(matrix[d.x][d.y].z/1000);
		if (color_idx > 10)
			color_idx = 10;
		//return nodes[d.x].group == nodes[d.y].group ? "#eee" : color[color_idx];
		return nodes[d.x].name == nodes[d.y].name ? "#eee" : color[color_idx];
	})
	
        .on("mouseover", mouseover)
        .on("mouseout", mouseout)
        .on("click", onclick);
        //.on("contextmenu", onclick);
  }

  function mouseover(p) {
    d3.selectAll(".row text").classed("active", function(d, i) { return i == p.y; });
    d3.selectAll(".column text").classed("active", function(d, i) { return i == p.x; });
      d3.select(this).insert("title").text(nodes[p.y].name + "--" + nodes[p.x].name);
      d3.select(this.parentElement)
	  .append("rect")
	  .attr("class", "highlight")
	  .attr("width", width)
	  .attr("height", x.rangeBand());
      d3.select("#col"+p.x)
	  .append("rect")
	  .attr("class", "highlight")
	  .attr("x", -width)
	  .attr("width", width)
	  .attr("height", x.rangeBand());
  }

  function mouseout(p) {
    d3.selectAll("text").classed("active", false);
      d3.select(this).select("title").remove();
      d3.selectAll(".highlight").remove();
  }

  contextMenuShowing = false;

  function onclick(p) {
	  d3.event.preventDefault();
	  d3.event.stopPropagation();
	  console.log("onclick called\n");
	  console.log("src_svc=" + nodes[p.y].name + "\n");
	  console.log("dst_svc=" + nodes[p.x].name + "\n");
	  console.log("\n");
      //dopopup(p);
	  console.log("contextMenuShowing=" + contextMenuShowing + "\n");
    if (contextMenuShowing) {
	    d3.select(".popup").remove();
    } else {
	    popup = d3.select(".canvas")
		    .append("div")
		    .attr("class", "popup")
		    .style("left", "30px")
		    .style("top", "30px");

	    console.log(popup.toString());
	    popup.append("h3").text("Instance matrix");
	    popup.append("p").text(
			    "source : " + nodes[p.y].name + ", " +
			    " target : " + nodes[p.x].name);
    // set margin, width and height
    var margin1 = {top: 40, right: 0, bottom: 10, left: 40},
	width1 = 360,
	height1 = 360;
    var x = d3.scale.ordinal().rangeBands([0, width1]),
        z = d3.scale.linear().domain([0, 4]).clamp(true),
        c = d3.scale.category10().domain(d3.range(10));

    svgInstMatrix = popup.append("svg")
  			.attr("width", width1 + margin1.left + margin1.right)
			.attr("height", height1 + margin1.top + margin1.bottom)
			//.style("margin-left", -margin1.left + "px")
			.style("margin-left", -40 + "px")
			.style("margin-top",-50 +"px") // Meena Debug
			.append("g")
			.attr("transform", "translate(" + margin1.left + "," + margin1.top + ")");
    build_instance_matrix(json, svgInstMatrix, nodes[p.y].name, nodes[p.x].name);
    }
    contextMenuShowing = !contextMenuShowing;
  }

    var currentOrder = 'name';

    function order(value) {
	var o = orders[value];
	currentOrder = value;
	
	if (typeof o === "function") {
	    orders[value] = o.call();
	}
	x.domain(orders[value]);

	var t = svg.transition().duration(1500);

	t.selectAll(".row")
            .delay(function(d, i) { return x(i) * 4; })
            .attr("transform", function(d, i) { return "translate(0," + x(i) + ")"; })
	    .selectAll(".cell")
            .delay(function(d) { return x(d.x) * 4; })
            .attr("x", function(d) { return x(d.x); });

	t.selectAll(".column")
            .delay(function(d, i) { return x(i) * 4; })
            .attr("transform", function(d, i) { return "translate(" + x(i) + ")rotate(-90)"; });
    }

    matrix.order = order;

    var timeout = setTimeout(function() {}, 1000);
    matrix.timeout = timeout;
    
  return matrix;
}


function loadJson(json) {
    // MEENA DEBUG
    console.log(json.nodes[0]);
    console.log(json.links[0]);

    var mat = build_matrix(json, svgMainInst, "all", "all");

    d3.select("#order").on("change", function() {
	mat.order(this.value);
    });
}
