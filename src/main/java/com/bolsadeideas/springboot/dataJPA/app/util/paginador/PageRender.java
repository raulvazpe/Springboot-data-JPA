package com.bolsadeideas.springboot.dataJPA.app.util.paginador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;
	private Page<T> page;

	private int totalPaginas;
	private int numeroElementosPorPagina;
	private int paginaActual;
	private List<PageItem> paginas;

	public PageRender(String url, Page<T> page) {

		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<PageItem>();

		numeroElementosPorPagina = page.getSize();
		totalPaginas = page.getTotalPages();
		paginaActual = page.getNumber() + 1; // Nos devuelve la pagina actual (Empieza en la pagina 0 y por eso le
												// sumamos el 1)

		int desde = 0;
		int hasta = 0;

		// Calculo para saber que paginas hay que mostrar
		if (totalPaginas < numeroElementosPorPagina) {
			// significa que hay pocas paginas (mostraremos el paginador completo)
			desde = 0;
			hasta = totalPaginas;
		} else {
			if (paginaActual - 2 <= 0) {
				desde = 1;
				hasta = 5;
			} else {
				if (paginaActual + 4 >= totalPaginas) {
					desde = paginaActual - 2;
					hasta = totalPaginas;
				} else {
					desde = paginaActual = -2;
					hasta = paginaActual = +2;
				}
			}
		}

		// Las paginas que mostraremos en el paginador
		for (int i = desde; i <= hasta; i++) {
			paginas.add(new PageItem(i, paginaActual == i));

		}

	}

	public String getUrl() {
		return url;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}

	// Metodos para navegar entre paginas
	public boolean isFirst() {
		return page.isFirst();
	}

	public boolean isHasNext() {
		return page.hasNext();
	}

	public boolean isLast() {
		return page.isLast();
	}

	public boolean isHasPrevious() {
		return page.hasPrevious();
	}

}
