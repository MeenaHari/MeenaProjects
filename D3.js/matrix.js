
function matrix(json) {
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
        .on("mouseout", mouseout);
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
  /*  console.log(json.nodes[0]);
    console.log(json.links[0]);
 */
    var mat = matrix(json);

    d3.select("#order").on("change", function() {
	mat.order(this.value);
    });
}
