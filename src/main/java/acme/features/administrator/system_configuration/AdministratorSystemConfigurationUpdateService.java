
package acme.features.administrator.system_configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Administrator;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.system_configuration.SystemConfiguration;

@Service
public class AdministratorSystemConfigurationUpdateService extends AbstractService<Administrator, SystemConfiguration> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorSystemConfigurationRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Administrator.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		SystemConfiguration object;

		object = this.repository.findCurrentSystemConfiguration();

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final SystemConfiguration object) {
		assert object != null;

		super.bind(object, "systemCurrency", "acceptedCurrencies", "spamWords", "spamThreshold");
	}

	@Override
	public void validate(final SystemConfiguration object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("systemCurrency"))
			super.state(object.getAcceptedCurrencies().contains(object.getSystemCurrency()), "systemCurrency", "administrator.system-configuration.form.error.system-currency");
	}

	@Override
	public void perform(final SystemConfiguration object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final SystemConfiguration object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "systemCurrency", "acceptedCurrencies", "spamWords", "spamThreshold");

		super.getResponse().addData(dataset);
	}

}
