<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
</head>

<div>
	<button id="prev">Previous</button>
	<button id="next">Next</button>
	&nbsp; &nbsp;
	<span>Page: <span id="page_num"></span> / <span id="page_count"></span></span>
</div>

<canvas id="the-canvas"></canvas>
<script src="${ctxStatic}/pdfjs/build/pdf.js"></script>
<script src="${ctxStatic}/pdfjs/build/pdf.worker.js"></script>
<script>
	var url = '${ctxStatic}/pdfjs/liuchengtu.pdf';

	var pdfDoc = null,
			pageNum = 1,
			pageRendering = false,
			pageNumPending = null,
			scale = 1,//缩放比例
			canvas = document.getElementById('the-canvas'),
			ctx = canvas.getContext('2d');

	/**
	 * Get page info from document, resize canvas accordingly, and render page.
	 * @param num Page number.
	 */
	function renderPage(num) {
		pageRendering = true;
		// Using promise to fetch the page
		pdfDoc.getPage(num).then(function(page) {
			var viewport = page.getViewport(scale);
			var CSS_UNITS = 96.0 / 72.0;
			canvas.height = Math.floor(viewport.height * CSS_UNITS);
			canvas.width = Math.floor(viewport.width * CSS_UNITS) ;

			// Render PDF page into canvas context
			var renderContext = {
				transform: [CSS_UNITS, 0, 0, CSS_UNITS, 0, 0],
				canvasContext: ctx,
				viewport: viewport
			};
			var renderTask = page.render(renderContext);

			// Wait for rendering to finish
			renderTask.promise.then(function() {
				pageRendering = false;
				if (pageNumPending !== null) {
					// New page rendering is pending
					renderPage(pageNumPending);
					pageNumPending = null;
				}
			});
		});

		// Update page counters
		document.getElementById('page_num').textContent = num;
	}

	/**
	 * If another page rendering in progress, waits until the rendering is
	 * finised. Otherwise, executes rendering immediately.
	 */
	function queueRenderPage(num) {
		if (pageRendering) {
			pageNumPending = num;
		} else {
			renderPage(num);
		}
	}

	/**
	 * Displays previous page.
	 */
	function onPrevPage() {
		if (pageNum <= 1) {
			return;
		}
		pageNum--;
		queueRenderPage(pageNum);
	}
	document.getElementById('prev').addEventListener('click', onPrevPage);

	/**
	 * Displays next page.
	 */
	function onNextPage() {
		if (pageNum >= pdfDoc.numPages) {
			return;
		}
		pageNum++;
		queueRenderPage(pageNum);
	}
	document.getElementById('next').addEventListener('click', onNextPage);

	/**
	 * Asynchronously downloads PDF.
	 */
	PDFJS.getDocument(url).then(function(pdfDoc_) {
		pdfDoc = pdfDoc_;
		document.getElementById('page_count').textContent = pdfDoc.numPages;

		// Initial/first page rendering
		renderPage(pageNum);
	});
</script>
</html>