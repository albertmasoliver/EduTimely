package org.acme.timetable.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import org.acme.timetable.Timetable;
import org.optaplanner.core.api.solver.SolverManager;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

// REST resource that exposes the timetable solving endpoint.
@Path("/timetable")
public class TimetableResource {

    // Injects the SolverManager to solve timetable problems
    @Inject
    SolverManager<Timetable, UUID> solverManager;

    // Solves the given timetable problem and returns the best solution
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Timetable solve(Timetable problem) throws InterruptedException, ExecutionException {
        UUID problemId = UUID.randomUUID();
        return solverManager.solve(problemId, problem).getFinalBestSolution();
    }
}
