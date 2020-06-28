package hiking_app.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import hiking_app.entity.EventEntity;
import hiking_app.repository.EventRepository;
import hiking_app.request.CreateEventModelMapping;
import hiking_app.response.EventModel;
import hiking_app.response.assembler.EventModelAssembler;
import hiking_app.util.ObjectJsonConverter;

@RestController
public class EventController {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private EventModelAssembler eventModelAssembler;

	@GetMapping(path = "/events", produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getEvents() {
		Iterable<EventEntity> eventList = eventRepository.findAll();

		return ObjectJsonConverter.eventsToString((Object) eventList);
	}

	@GetMapping(path = "events/{eventName}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<EventModel> getEventByName(@PathVariable String eventName) throws Exception {
		EventEntity event;
		
		if ((event = eventRepository.getEventEntityByName(eventName)) == null)
			throw new Exception("Event not found");
		else {
			ResponseEntity<EventModel> entity = new ResponseEntity<>(eventModelAssembler.toModel(event), HttpStatus.OK);

			return entity;
		}
	}

	@PostMapping(path = "events/registration", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<EventModel> createEvent(@RequestBody CreateEventModelMapping eventDetails) throws Exception {
		if (eventRepository.getEventEntityByName(eventDetails.getName()) != null)
			throw new Exception("Event with that name already exist");

		EventEntity returnValueEvent = new EventEntity();
		EventEntity createdEvent = new EventEntity();
		ModelMapper modelMapper = new ModelMapper();
		createdEvent = modelMapper.map(eventDetails, EventEntity.class);

		returnValueEvent = eventRepository.save(createdEvent);
		ResponseEntity<EventModel> returnValue = new ResponseEntity<>(eventModelAssembler.toModel(returnValueEvent),
				HttpStatus.OK);

		return returnValue;
	}

	@DeleteMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, path = "/events/{eventName}")
	public void deleteEvent(@PathVariable String eventName)
			throws Exception {
		EventEntity event = eventRepository.getEventEntityByName(eventName);
		if (event == null)
			throw new Exception("Event wasnt found");		

		eventRepository.delete(event);
	}

}
