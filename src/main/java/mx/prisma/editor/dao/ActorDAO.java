package mx.prisma.editor.dao;

import mx.prisma.editor.model.Actor;

public class ActorDAO extends ElementoDAO {

    public void registrarActor(Actor actor) throws Exception {
    	super.registrarElemento(actor);
    }
}
