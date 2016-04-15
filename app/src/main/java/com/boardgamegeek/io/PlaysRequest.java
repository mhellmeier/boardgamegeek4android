package com.boardgamegeek.io;

import com.boardgamegeek.model.PlaysResponse;

public class PlaysRequest {
	private static final int TYPE_ALL = 0;
	public static final int TYPE_MIN = 1;
	public static final int TYPE_MAX = 2;

	private final BggService bggService;
	private final int type;
	private final String username;
	private final int page;
	private final String date;

	public PlaysRequest(BggService service, int type, String username, int page, String date) {
		this.bggService = service;
		this.type = type;
		this.username = username;
		this.date = date;
		this.page = page;
	}

	public PlaysRequest(BggService service, String username, int page) {
		this.bggService = service;
		this.type = TYPE_ALL;
		this.username = username;
		this.page = page;
		this.date = "";
	}

	public PlaysResponse execute() {
		if (type == PlaysRequest.TYPE_MIN) {
			return bggService.playsByMinDate(username, date, page);
		} else if (type == PlaysRequest.TYPE_MAX) {
			return bggService.playsByMaxDate(username, date, page);
		} else {
			return bggService.plays(username, page);
		}
	}
}
