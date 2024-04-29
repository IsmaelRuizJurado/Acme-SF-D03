
package acme.features.any.training_module;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Any;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.training_module.TrainingModule;

@Service
public class AnyTrainingModuleListService extends AbstractService<Any, TrainingModule> {

	@Autowired
	protected AnyTrainingModuleRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Collection<TrainingModule> objects;
		objects = this.repository.findPublishedTrainingModules();

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final TrainingModule object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "details", "basicLevel");
		dataset.put("projectCode", object.getProject().getCode());
		super.getResponse().addData(dataset);
	}
}
