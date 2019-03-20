package br.feevale.projetosismu.bean;

import br.feevale.projetosismu.entity.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CategoriaBean {

    private Categoria categoria;
    private List<Categoria> categorias = new ArrayList<>();
    
    public void adicionar(){
        categorias.add(categoria);
        categoria = new Categoria();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}
