
package acme.features.client.dashboard;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Principal;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.datatypes.Stats;
import acme.forms.ClientDashboard;
import acme.roles.Client;

@Service
public class ClientDashboardShowService extends AbstractService<Client, ClientDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ClientDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		final ClientDashboard dashboard = new ClientDashboard();

		Principal principal;
		int userAccountId;
		principal = super.getRequest().getPrincipal();
		userAccountId = principal.getAccountId();
		final Client client = this.repository.findOneClientByUserAccountId(userAccountId);

		//contractBudgetStats

		final double findAverageContractBudget = this.repository.findAverageContractBudget(client).orElse(0.0);
		final double findMaxContractBudget = this.repository.findMaxContractBudget(client).orElse(0.0);
		final double findMinContractBudget = this.repository.findMinContractBudget(client).orElse(0.0);
		final double findLinearDevContractBudget = this.repository.findLinearDevContractBudget(client).orElse(0.0);
		final Stats contractBudgetStats = new Stats();
		contractBudgetStats.setAverage(findAverageContractBudget);
		contractBudgetStats.setMinimum(findMinContractBudget);
		contractBudgetStats.setMaximum(findMaxContractBudget);
		contractBudgetStats.setDeviation(findLinearDevContractBudget);
		dashboard.setContractBudgetStats(contractBudgetStats);

		//progressLogsCompleteness
		final Map<String, Integer> progressLogsCompleteness = new HashMap<String, Integer>();

		final Integer progressLogsWithRateBelow25 = this.repository.findNumOfprogressLogsLess25(client).orElse(0);
		final Integer progressLogsWithRate25To50 = this.repository.findNumOfProgressLogsWithRate25To50(client).orElse(0);
		final Integer progressLogsWithRate50To75 = this.repository.findNumOfProgressLogsWithRate50To75(client).orElse(0);
		final Integer progressLogsWithRateOver75 = this.repository.findNumOfProgressLogsWithRateOver75(client).orElse(0);

		progressLogsCompleteness.put("less25", progressLogsWithRateBelow25);
		progressLogsCompleteness.put("25to50", progressLogsWithRate25To50);
		progressLogsCompleteness.put("50to75", progressLogsWithRate50To75);
		progressLogsCompleteness.put("over75", progressLogsWithRateOver75);
		dashboard.setNumCompletenessProgressLogs(progressLogsCompleteness);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final ClientDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, "contractBudgetStats", "numCompletenessProgressLogs");

		super.getResponse().addData(dataset);
	}

}
