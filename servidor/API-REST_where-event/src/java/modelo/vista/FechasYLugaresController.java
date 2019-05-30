package modelo.vista;

import modelo.FechasYLugares;
import modelo.vista.util.JsfUtil;
import modelo.vista.util.JsfUtil.PersistAction;
import modelo.jpa.FechasYLugaresFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "fechasYLugaresController")
@SessionScoped
public class FechasYLugaresController implements Serializable {

    @EJB
    private modelo.jpa.FechasYLugaresFacade ejbFacade;
    private List<FechasYLugares> items = null;
    private FechasYLugares selected;

    public FechasYLugaresController() {
    }

    public FechasYLugares getSelected() {
        return selected;
    }

    public void setSelected(FechasYLugares selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getFechasYLugaresPK().setIdEvento(selected.getEventos().getIdEvento());
    }

    protected void initializeEmbeddableKey() {
        selected.setFechasYLugaresPK(new modelo.FechasYLugaresPK());
    }

    private FechasYLugaresFacade getFacade() {
        return ejbFacade;
    }

    public FechasYLugares prepareCreate() {
        selected = new FechasYLugares();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FechasYLugaresCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FechasYLugaresUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FechasYLugaresDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<FechasYLugares> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<FechasYLugares> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<FechasYLugares> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = FechasYLugares.class)
    public static class FechasYLugaresControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FechasYLugaresController controller = (FechasYLugaresController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fechasYLugaresController");
            return controller.getFacade().find(getKey(value));
        }

        modelo.FechasYLugaresPK getKey(String value) {
            modelo.FechasYLugaresPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new modelo.FechasYLugaresPK();
            key.setIdEvento(Integer.parseInt(values[0]));
            key.setFechaInicio(java.sql.Date.valueOf(values[1]));
            key.setUbicacion(values[2]);
            return key;
        }

        String getStringKey(modelo.FechasYLugaresPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdEvento());
            sb.append(SEPARATOR);
            sb.append(value.getFechaInicio());
            sb.append(SEPARATOR);
            sb.append(value.getUbicacion());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof FechasYLugares) {
                FechasYLugares o = (FechasYLugares) object;
                return getStringKey(o.getFechasYLugaresPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), FechasYLugares.class.getName()});
                return null;
            }
        }

    }

}
