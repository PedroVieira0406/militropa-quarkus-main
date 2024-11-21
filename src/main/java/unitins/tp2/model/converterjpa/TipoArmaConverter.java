package unitins.tp2.model.converterjpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import unitins.tp2.model.TipoArma;

@Converter(autoApply = true)
public class TipoArmaConverter implements AttributeConverter<TipoArma, Integer>{

    @Override
    public Integer convertToDatabaseColumn(TipoArma ta) {
        return (ta == null ? null : ta.getId());
    }

    @Override
    public TipoArma convertToEntityAttribute(Integer id) {
        return TipoArma.valueOf(id);
    }
    
}
