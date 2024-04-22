
package acme.features.developer.training_sessions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Principal;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.components.AuxiliarService;
import acme.entities.training_session.TrainingSession;
import acme.roles.Developer;

@Service
public class DeveloperTrainingSessionPublishService extends AbstractService<Developer, TrainingSession> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected DeveloperTrainingSessionRepository	repository;

	@Autowired
	protected AuxiliarService						auxiliarService;

	// AbstractService<Employer, Job> -------------------------------------


	@Override
	public void authorise() {
		TrainingSession object;
		int id;
		id = super.getRequest().getData("id", int.class);
		object = this.repository.findTrainingSessionById(id);
		final Principal principal = super.getRequest().getPrincipal();
		final int userAccountId = principal.getAccountId();
		super.getResponse().setAuthorised(object.getDeveloper().getUserAccount().getId() == userAccountId && object.isDraftMode());
	}

	@Override
	public void load() {
		TrainingSession object;
		int id;
		id = super.getRequest().getData("id", int.class);
		object = this.repository.findTrainingSessionById(id);
		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final TrainingSession object) {
		assert object != null;
		super.bind(object, "code", "startPeriod", "endPeriod", "location", "instructor", "contactEmail", "optionalLink");
	}

	@Override
	public void validate(final TrainingSession object) {
		assert object != null;
		if (!super.getBuffer().getErrors().hasErrors("startPeriod"))
			super.state(this.auxiliarService.validateDate(object.getStartPeriod()), "startPeriod", "developer.training-session.form.error.startPeriod");
		if (!super.getBuffer().getErrors().hasErrors("location"))
			super.state(this.auxiliarService.validateTextImput(object.getLocation()), "location", "developer.training-session.form.error.spam");
		if (!super.getBuffer().getErrors().hasErrors("instructor"))
			super.state(this.auxiliarService.validateTextImput(object.getInstructor()), "instructor", "developer.training-session.form.error.spam");
		if (!super.getBuffer().getErrors().hasErrors("endPeriod"))
			super.state(this.auxiliarService.validateDate(object.getEndPeriod()), "endPeriod", "developer.training-session.form.error.endPeriod");
	}

	@Override
	public void perform(final TrainingSession object) {
		object.setDraftMode(false);
		this.repository.save(object);
	}

	@Override
	public void unbind(final TrainingSession object) {
		assert object != null;
		Dataset dataset;
		dataset = super.unbind(object, "title", "description", "estimatedCostPerHour", "acceptanceCriteria", "priority", "optionalLink", "draftMode");
		super.getResponse().addData(dataset);
	}
}