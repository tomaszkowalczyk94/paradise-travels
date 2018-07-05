package paradiseTravels.service.localJourney;

import paradiseTravels.bean.LocalJourneyBean;
import paradiseTravels.model.LocalJourney;
import paradiseTravels.service.EntityService;

import javax.ws.rs.Path;

@Path("/local-journeys")
public class LocalJourneyService extends EntityService<LocalJourney, LocalJourneyBean> {
}
