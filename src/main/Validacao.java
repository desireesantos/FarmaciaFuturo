package start;

	import javax.faces.application.FacesMessage;
	import javax.faces.component.UIComponent;
	import javax.faces.context.FacesContext;
	import javax.faces.validator.Validator;
	import javax.faces.validator.ValidatorException;

	public class Validacao implements Validator {
		public void validate(FacesContext context, UIComponent component,
				Object value) throws ValidatorException {
			String usuario = (String) value;
			if (!usuario.equalsIgnoreCase("tester")) {
				FacesMessage message = new FacesMessage();
				message.setDetail("User " + usuario + " não  existe");
				message.setSummary("Login Incorreto");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		}
	
}
