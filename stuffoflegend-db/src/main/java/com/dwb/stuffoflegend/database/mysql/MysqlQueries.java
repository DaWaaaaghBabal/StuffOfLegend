package com.dwb.stuffoflegend.database.mysql;

import static com.dwb.stuffoflegend.database.core.QueryKey.CREATE_ABILITY;
import static com.dwb.stuffoflegend.database.core.QueryKey.CREATE_CIRCLE;
import static com.dwb.stuffoflegend.database.core.QueryKey.CREATE_CLASS;
import static com.dwb.stuffoflegend.database.core.QueryKey.CREATE_TRACK;
import static com.dwb.stuffoflegend.database.core.QueryKey.CREATE_USER;
import static com.dwb.stuffoflegend.database.core.QueryKey.GET_ABILITY_TYPES;
import static com.dwb.stuffoflegend.database.core.QueryKey.GET_CLASSES;
import static com.dwb.stuffoflegend.database.core.QueryKey.GET_PROGRESSIONS;
import static com.dwb.stuffoflegend.database.core.QueryKey.GET_TRACKS;
import static com.dwb.stuffoflegend.database.core.QueryKey.GET_TRACKS_FOR_CLASS;
import static com.dwb.stuffoflegend.database.core.QueryKey.LAST_INSERTED_ID;
import static com.dwb.stuffoflegend.database.core.QueryKey.LOGIN;
import static com.dwb.stuffoflegend.database.core.QueryKey.TRANSLATE_ABILITY;
import static com.dwb.stuffoflegend.database.core.QueryKey.TRANSLATE_CLASS;

import com.dwb.stuffoflegend.database.core.Queries;
import com.dwb.stuffoflegend.database.core.QueryKey;

public class MysqlQueries extends Queries {
	private static final long serialVersionUID = 23246898752804700L;

	public MysqlQueries() {
		super();
		put(LAST_INSERTED_ID, "select LAST_INSERT_ID() as id;");
		put(LOGIN, "select id, login, email from users where login='${login}' and password='${password}';");
		put(CREATE_USER, "insert into users (id, login, email, password) values (NULL, '${login}','${email}','${password}';");
		put(GET_TRACKS, "select t.id, tlt.name from tracks as t inner join _translations_tracks as tlt on t.id = tlt.track and tlt.language='${language}';");
		put(GET_TRACKS_FOR_CLASS, "select track, progression from track_choices where class='${class}';");
		put(GET_PROGRESSIONS, "select p.id, p.offset, tlp.name from progressions as p inner join _translations_progressions as tlp on p.id=tlp.progression and tlp.language='${language}';");
		put(GET_CLASSES, "select c.id, c.bab, c.hp, c.skills, tlc.name from classes as c inner join _translations_classes as tlc on tlc.class = c.id and tlc.language='${language}';");		
		put(CREATE_CLASS, "insert into classes (id, hp, skills, bab) values (NULL, '${hp}', '${skills}', '${bab}');");
		put(TRANSLATE_CLASS, "insert into _translations_classes (class, language, name) values ('${id}', '${language}', '${name}');");
		put(GET_ABILITY_TYPES, "select at.id, tlat.label_short, tlat.label_long from ability_types as at inner join _translations_ability_types as tlat on tlat.ability_type=a.id and tlat.language='${language}'");
		put(CREATE_ABILITY, "insert into abilities (id, circle_choice, ability_type) values (NULL, '${circle_choice}', '${ability_type}');");
		put(TRANSLATE_ABILITY, "insert into _translations_abilities (ability, language, name, rules_text) values ('${ability}', '${language}', '${name}', '${text}');");
		put(CREATE_TRACK, "insert into tracks (id) values(NULL)");
		put(CREATE_CIRCLE, "insert into circles (id, track, number, ability_type) values (NULL, '${track}', '${number}', '${type}');");
		put(QueryKey.CREATE_CIRCLE_CHOICE, "insert into circle_choices (id, circle) values (NULL, '${circle}');");
		
		
	}
	

}
