
package acme.features.any.progress_logs;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Any;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.progress_logs.ProgressLogs;

@Service
public class AnyProgressLogsListService extends AbstractService<Any, ProgressLogs> {

	@Autowired
	protected AnyProgressLogsRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Collection<ProgressLogs> objects;
		int masterId = super.getRequest().getData("masterId", int.class);
		objects = this.repository.findPublishedProgressLogsByContract(masterId);

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final ProgressLogs object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "recordId", "completeness", "responsiblePerson");

		super.getResponse().addData(dataset);
	}
}
