export const ESTADOS = [
    {
        id: 30,
        desc: 'Activo'
    },

    {
        id: 80,
        desc: 'Eliminado'
    }

]

export const ENDPOINTS = {

    USUARIOS: {
        GET: 'api/usuario',
        POST: 'api/usuario'
    },

    ORDENES_TRABAJO: {
        GET: 'api/ordenes-trabajo',
        POST: 'api/ordenes-trabajo'
    },

    SECTORES: {
        GET: 'api/sector',
        POST: 'api/sector'
    }


}

export const MENUS = {

    

}

export const PRIORIDADES = [

    {
        id : '1',
        desc: 'URGENTE'
    },
    {
        id: '2',
        desc: 'PRODUCCION PARADA'
    },
    {
        
        id: '3',
        desc: 'EN LO INMEDIATO:'
    },
    {
        id: '4',
        desc: 'FALLA INMINENTE'
    },
    {
        id: '5',
        desc: 'A PROGRAMAR'
    },
    {
        id: '6',
        desc: 'REQUIERE PARAR LA PRODUCCION'
    },
    {
        id: '7',
        desc: 'N/E'
    }
    
    
    
    
    
    

]

export const PLANTAS = [

    {
        id: '1',
        desc: 'Bavio'
    },
    {
        id: '2',
        desc: 'Ruta 50'
    }
]

export const TIPO = [

    {
        id: '1',
        desc: 'PREVENTIVO'
    },
    {
        id: '2',
        desc: 'CORRECTIVO'
    },
    {
        id: '3',
        desc: 'MEJORATIVO'
    }


]

export const ESTADO_ORDEN = [
    {
        id: '1',
        desc: 'PENDIENTE'
    },
    {
        id: '2',
        desc: 'OK'
    }
]


////View/////

export const ESTADOTABLE = {
    A: {
        ID: 'PENDIENTE'
    },
    B:{
        ID: 'OK'
    }
}

export const TIPOTABLE = {
    A: {
        ID: 'Preventivo'
    },
    B:{
        ID: 'Correctivo'
    },
    C:{
        ID: 'Mejorativo'
    }
}

export const PLANTATABLE = {
    A: {
        ID: 'Bavio'
    },
    B: {
        ID: 'Ruta 50'
    }
}

export const PRIORIDADESTABLE = {
    A: {
        ID: 'Urgente'
    },
    B: {
        ID: 'Produccion parada'
    },
    C: {
        ID: 'En lo inmediato'
    },
    D: {
        ID: 'Falla inminente'
    },
    E: {
        ID: 'A programar'
    },
    F: {
        ID: 'Requiere parar la produccion'
    },
    G: {
        ID: 'N/E'
    }
    
}