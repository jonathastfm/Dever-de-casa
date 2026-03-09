const width = window.innerWidth;
const height = window.innerHeight;

const svg = d3.select("#graph-container")
    .append("svg")
    .attr("width", "100%")
    .attr("height", "100%")
    .attr("viewBox", [0, 0, width, height]);

const g = svg.append("g");

// Zoom behavior
svg.call(d3.zoom()
    .extent([[0, 0], [width, height]])
    .scaleExtent([0.1, 8])
    .on("zoom", ({ transform }) => {
        g.attr("transform", transform);
    }));

async function renderGraph() {
    try {
        // We look for graph_data.json in the parent directory since visualizer is a subfolder
        const response = await fetch('./graph_data.json');
        const data = await response.json();

        d3.select("#node-count").text(`Nós: ${data.nodes.length}`);
        d3.select("#edge-count").text(`Arestas: ${data.links.length}`);

        const simulation = d3.forceSimulation(data.nodes)
            .force("link", d3.forceLink(data.links).id(d => d.id).distance(150))
            .force("charge", d3.forceManyBody().strength(-300))
            .force("center", d3.forceCenter(width / 2, height / 2))
            .force("collision", d3.forceCollide().radius(30));

        const link = g.append("g")
            .attr("class", "links")
            .selectAll("line")
            .data(data.links)
            .join("line")
            .attr("class", "link");

        const node = g.append("g")
            .attr("class", "nodes")
            .selectAll("g")
            .data(data.nodes)
            .join("g")
            .attr("class", "node")
            .call(drag(simulation));

        node.append("circle")
            .attr("r", 20)
            .attr("fill", "url(#node-gradient)");

        // Add glow effect
        const defs = svg.append("defs");
        const gradient = defs.append("linearGradient")
            .attr("id", "node-gradient")
            .attr("x1", "0%").attr("y1", "0%")
            .attr("x2", "100%").attr("y2", "100%");
        gradient.append("stop").attr("offset", "0%").attr("stop-color", "#38bdf8");
        gradient.append("stop").attr("offset", "100%").attr("stop-color", "#818cf8");

        node.append("text")
            .attr("dy", 4)
            .text(d => d.id);

        simulation.on("tick", () => {
            link
                .attr("x1", d => d.source.x)
                .attr("y1", d => d.source.y)
                .attr("x2", d => d.target.x)
                .attr("y2", d => d.target.y);

            node
                .attr("transform", d => `translate(${d.x},${d.y})`);
        });

    } catch (error) {
        console.error("Error loading graph data:", error);
        alert("Erro ao carregar os dados do grafo. Certifique-se de que o arquivo 'graph_data.json' existe.");
    }
}

function drag(simulation) {
    function dragstarted(event) {
        if (!event.active) simulation.alphaTarget(0.3).restart();
        event.subject.fx = event.subject.x;
        event.subject.fy = event.subject.y;
    }

    function dragged(event) {
        event.subject.fx = event.x;
        event.subject.fy = event.y;
    }

    function dragended(event) {
        if (!event.active) simulation.alphaTarget(0);
        event.subject.fx = null;
        event.subject.fy = null;
    }

    return d3.drag()
        .on("start", dragstarted)
        .on("drag", dragged)
        .on("end", dragended);
}

document.getElementById("refresh-btn").addEventListener("click", () => {
    location.reload();
});

window.addEventListener("resize", () => {
    // Basic resize handling
    location.reload();
});

renderGraph();
