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

    SERVER: {
        serve: 'https://metaltronik.herokuapp.com/',
        // local: 'http://localhost:8080/'
        // heroku: 'https://metaltronik.herokuapp.com/'
    },

    PERSONAL: {
        GET: 'api/personal',
        GETID: 'api/personal/',
        POST: 'api/personal',
        PUT: 'api/personal/',
    },

    USUARIOS: {
        GET: 'api/usuario',
        GETID: 'api/usuario/',
        POST: 'api/usuario/create/',
        LOGIN: 'api/usuario/login'
    },

    ORDENES_TRABAJO: {
        GET: 'api/ordenes-trabajo',
        GETID: 'api/ordenes-trabajo/',
        POST: 'api/ordenes-trabajo',
        PUT: 'api/ordenes-trabajo/'
    },

    MAQUINAS: {
        GET: 'api/maquina',
        GETID: 'api/maquina/',
        POST: 'api/maquina',
        PUT: 'api/maquina/',
        DELETE: 'api/maquina/'
    },

    SECTORES: {
        GET: 'api/sector',
        GETID: 'api/sector/',
        POST: 'api/sector',
        PUT: 'api/sector/'
    },

    PLANTAS: {
        GET: 'api/planta',
        GETID: 'api/planta/',
        POST: 'api/planta',
        PUT: 'api/planta/'
    },

    MANTENIMIENTOCORRECTIVO: {
        GET: 'api/mantenimiento-correctivo',
        GETID: 'api/mantenimiento-correctivo/',
        POST: 'api/mantenimiento-correctivo',
        PUT: 'api/mantenimiento-correctivo/'
    },

    TIPOS: {
        GET: 'api/tipo/nombre/',
        GETALL: 'api/tipo/tipos',
        GETID: 'api/tipo/',
        POST: 'api/tipo',
        PUT: 'api/tipo/'
    },

    PRIORIDADES: {
        GET: 'api/prioridades',
        GETID: 'api/prioridades/',
        POST: 'api/prioridades',
        PUT: 'api/priodidades/'
    },

    REPUESTOS: {
        GET: 'api/repuesto',
        GETID: 'api/repuesto/',
        GETBYMAQUINA: 'api/repuesto/maquina/',
        POST: 'api/repuesto',
        PUT: 'api/repuesto/'
    },

    REPUESTOMAQUINA: {
        POST: 'api/asociacion/vincular/maquina-parte',
        GET: 'api/asociacion/vinculados',
        GETID: 'api/asociacion/',
        GETSINASOC: 'api/asociacion/sin-vincular',
        PUT: 'api/asociacion/'
    },

    ENTRADA: {
        GET: 'api/entrada',
        GETID: 'api/entrada/',
        POST: 'api/entrada'
    },

    SALIDA: {
        GET: 'api/salida',
        POST: 'api/salida'
    },

    AJUSTE_STOCK: {
        GET: 'api/ajustes',
        POST: 'api/ajustes/'
    },

    TAREA: {
        GET: 'api/tarea',
        GETID: 'api/tarea/',
        POST: 'api/tarea',
        PUT: 'api/tarea/',
        GETPUTHISTORIAL: 'api/preventivo/tarea-historial/'
    },

    REGISTRO: {
        POST: 'api/registro',
        GET: 'api/registro/planificar/',
        GETSAVES: 'api/registro/actual/',
        GETID: 'api/registro/',
        PUT: 'api/registro/'
    },

    PARTE: {
        POST: 'api/parte',
        DELETE: 'api/parte/',
        GET: 'api/parte',
        GETBYMAQUINA: 'api/maquina/partesByIdMaquina/',
        PUT: 'api/maquina/vincular/'
    },

    INDICADORES: {
        ///Graficos Ordenes///
        USUARIOS: 'api/indicadores/formula/1/personal',
        SECTORES : 'api/indicadores/formula/1/sector',
        PIE_TIPOS: 'api/indicadores/formula/Torta/tipo',
        PIE_PRIORIDADES: 'api/indicadores/formula/Torta/prioridad',
        USUARIOS2: 'api/indicadores/formula/2/personal',

        ///Graficos Correctivos///
        LINE_CHART: 'api/indicadores/formula/Line-chart/maquina'
    },
    
    CARGO: {
        POST: 'api/cargo',
        GET: 'api/cargo',
        GETBYID: 'api/cargo/',
        PUT: 'api/cargo/',
        DELETE: 'api/cargo/'
    }
}

export const UNIDADES = [
    {
        id: "Kilos",
        descripcion: "Kilos"
    },
    {
        id: "Metros",
        descripcion: "Metros"
    },
    {
        id: "Litros",
        descripcion: "Litros"
    },
    {
        id: "Piezas",
        descripcion: "Piezas"
    },
]

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
        desc: 'EN LO INMEDIATO'
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